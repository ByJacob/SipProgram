package pl.edu.pwr.weka.sipprogram.gui.view

import com.jfoenix.controls.JFXButton
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import javafx.geometry.Pos
import javafx.scene.control.ScrollPane
import kfoenix.jfxbutton
import kfoenix.jfxlistview
import pl.edu.pwr.weka.sipprogram.gui.controller.ProcessConnectionController
import pl.edu.pwr.weka.sipprogram.gui.model.ProcessConnectionModel
import tornadofx.*

class ProcessConnectionView : View("ProcessConnection") {
    val controller: ProcessConnectionController by inject()
    val model = ProcessConnectionModel()
    override val root = vbox()

    init {
        with(root) {
            borderpane {
                center {
                    vbox {
                        subscribe<OpenFormRequestEvent> { it ->
                            clear()
                            scrollpane {
                                isFitToWidth = true
                                vbarPolicy = ScrollPane.ScrollBarPolicy.ALWAYS
                                isFitToHeight = true
                                add(controller.formRequestFragmentList[it.selectedIndex])
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
                        jfxbutton("Dodaj", JFXButton.ButtonType.RAISED) {
                            action {
                                controller.formRequestFragmentList.add(find(Scope()))
                                fire(OpenFormRequestEvent(controller.formRequestFragmentList.lastIndex))
                            }
                        }
                        jfxbutton("Wyślij", JFXButton.ButtonType.RAISED) {
                            action {
                                controller.sendRequests()
                            }
                        }
                        paddingAll = 10.0
                    }
                }
                paddingAll = 20.0
                left {
                    jfxlistview(controller.formRequestFragmentList) {
                        borderpaneConstraints {
                            marginBottom = 20.0
                        }
                        isShowTooltip = true
                        bindSelected(model.formRequestFragment)
                        cellFormat {
                            graphic = label(it.model.formRequest.method.toString()) {
                                val iconOne =
                                        if (it.model.isSendingRequest.value)
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
                            fire(OpenFormRequestEvent(controller.formRequestFragmentList.indexOf(it)))
                        }
                        subscribe<RefreshListFormRequestEvent> {
                            refresh()
                        }
                    }
                }
            }
        }
    }

    class OpenFormRequestEvent(val selectedIndex: Int) : FXEvent()

    class RefreshListFormRequestEvent : FXEvent()
}
