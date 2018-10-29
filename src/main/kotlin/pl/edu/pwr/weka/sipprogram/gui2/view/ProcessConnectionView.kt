package pl.edu.pwr.weka.sipprogram.gui2.view

import com.jfoenix.controls.JFXButton
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.control.ScrollPane
import kfoenix.jfxbutton
import kfoenix.jfxtextfield
import pl.edu.pwr.weka.sipprogram.gui2.controller.ProcessConnectionController
import pl.edu.pwr.weka.sipprogram.gui2.model.FormBaseRequestModel
import pl.edu.pwr.weka.sipprogram.gui2.view.fragment.FormRequestFragment
import tornadofx.*

class ProcessConnectionView : View("ProcessConnection") {
    val controller: ProcessConnectionController by inject()
    lateinit var centerContainer: Node
    override val root = vbox()

    init {
        with(root) {
            borderpane {
                center {
                    centerContainer = this
                    vbox {
                        subscribe<NewFormEvent> {
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
                                    fire(NewFormEvent(it))
                                }
                            }
                        }
                        jfxbutton("Wyślij", JFXButton.ButtonType.RAISED) {
                            action {
                                controller.sendAllRequest
                            }
                        }
                        paddingAll = 10.0
                    }
                }
                paddingAll = 20.0
                left {
                    //                    jfxlistview<StackPane>(controller.model.listSackPaneObservable) {
//                        borderpaneConstraints {
//                            marginBottom = 20.0
//                        }
//                        isShowTooltip = true
//                        bindSelected(controller.model.selectedStackPane)
//                    }
                    hbox { label("TEST") }
                }
            }
        }
    }

    class NewFormEvent(val model: FormBaseRequestModel) : FXEvent()
}
