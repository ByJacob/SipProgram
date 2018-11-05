package pl.edu.pwr.weka.sipprogram.gui2.view

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXListView
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.control.ScrollPane
import kfoenix.jfxbutton
import kfoenix.jfxlistview
import kfoenix.jfxtextfield
import pl.edu.pwr.weka.sipprogram.gui2.controller.ProcessConnectionController
import pl.edu.pwr.weka.sipprogram.gui2.model.FormRequestModel
import pl.edu.pwr.weka.sipprogram.gui2.view.fragment.FormRequestFragment
import tornadofx.*

class ProcessConnectionView : View("ProcessConnection") {
    val controller: ProcessConnectionController by inject()
    lateinit var centerContainer: Node
    lateinit var leftListRequest: JFXListView<FormRequestModel>
    override val root = vbox()

    init {
        with(root) {
            borderpane {
                center {
                    centerContainer = this
                    vbox {
                        subscribe<OpenFormRequestEvent> {
                            clear()
                            scrollpane {
                                isFitToWidth = true
                                val fragmentScope = Scope()
                                setInScope(it.model, fragmentScope)
                                vbarPolicy = ScrollPane.ScrollBarPolicy.ALWAYS
                                isFitToHeight = true
                                add(find<FormRequestFragment>(fragmentScope))
                            }
                        }
                    }
                }
                top {
                    hbox {
                        alignment = Pos.CENTER
                        borderpaneConstraints {
                            alignment = Pos.CENTER
                        }
                        jfxtextfield {
                            alignment = Pos.CENTER
                            isLabelFloat = true
                            promptText = "SerwerIP:Port"
                        }
                        jfxbutton("Dodaj", JFXButton.ButtonType.RAISED) {
                            action {
                                runAsync {
                                    controller.createRequestForm
                                } ui {
                                    controller.model.processConnection.listFormRequest.add(it)
                                    fire(OpenFormRequestEvent(it))
                                    leftListRequest.selectionModel.select(
                                            controller.model.processConnection.listFormRequest.lastIndex)
                                }
                            }
                        }
                        jfxbutton("Wyślij", JFXButton.ButtonType.RAISED) {
                            action {
                                runAsync {
                                    controller.sendAllRequest()
                                }
                            }
                        }
                        paddingAll = 10.0
                    }
                }
                paddingAll = 20.0
                left {
                    jfxlistview(controller.model.listFormRequest) {
                        leftListRequest = this
                        borderpaneConstraints {
                            marginBottom = 20.0
                        }
                        isShowTooltip = true
                        bindSelected(controller.model.selectedFormRequest)
                        cellFormat {
                            graphic = label(it.formRequest.request.toString()) {
                                val iconOne =
                                        if (it.formRequest.isSendingRequest)
                                            FontAwesomeIconView(FontAwesomeIcon.ARROW_RIGHT)
                                        else
                                            FontAwesomeIconView(FontAwesomeIcon.ARROW_LEFT)
                                iconOne.glyphSize = 18
                                val iconTwo = FontAwesomeIconView(FontAwesomeIcon.SERVER)
                                iconTwo.glyphSize = 18
                                style {
                                    fontSize = 18.px
                                }
                                graphic = hbox {
                                    style {
                                        padding = box(0.px, 10.px, 0.px, 0.px)
                                        spacing = 5.px
                                        alignment = Pos.CENTER
                                    }
                                    add(iconOne)
                                    add(iconTwo)
                                }
                            }
                        }
                        onUserSelect(clickCount = 1) {
                            fire(OpenFormRequestEvent(it))
                        }
                        subscribe<AddNewElementEvent> {
                            val index = it.index
                            val model = it.model
                            controller.model.processConnection.listFormRequest.add(index, model)
                        }
                        subscribe<RefreshListFormRequestEvent> {
                            refresh()
                        }
                    }
                }
            }
        }
    }

    class OpenFormRequestEvent(val model: FormRequestModel) : FXEvent()

    class RefreshListFormRequestEvent : FXEvent()

    class AddNewElementEvent(val index: Int, val model: FormRequestModel) : FXEvent()
}
