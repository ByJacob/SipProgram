package pl.edu.pwr.weka.sipprogram.gui.view

import com.jfoenix.controls.JFXButton
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import javafx.geometry.Pos
import javafx.scene.control.ScrollPane
import javafx.scene.layout.Priority
import kfoenix.jfxbutton
import kfoenix.jfxlistview
import pl.edu.pwr.weka.sipprogram.gui.controller.ProcessConnectionController
import pl.edu.pwr.weka.sipprogram.gui.model.ProcessConnectionModel
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.RequestEnum
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
                        subscribe<ClearFormRequestEvent> {
                            clear()
                        }
                        subscribe<OpenFormRequestEvent> { it ->
                            clear()
                            scrollpane {
                                isFitToWidth = true
                                vbarPolicy = ScrollPane.ScrollBarPolicy.ALWAYS
                                isFitToHeight = true
                                add(controller.formRequestFragmentList[it.selectedIndex])
                            }
                        }
                        vboxConstraints {
                            vgrow = Priority.ALWAYS
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
                                fire(SelectElementInListEvent(controller.formRequestFragmentList.lastIndex))
                            }
                        }
                        jfxbutton("Wyślij ostatni", JFXButton.ButtonType.RAISED) {
                            action {
                                controller.startSendLast()
                            }
                        }
                        jfxbutton("Wyślij wszystko", JFXButton.ButtonType.RAISED) {
                            action {
                                controller.startSendAll()
                            }
                        }
                        jfxbutton("Czyść wszystko", JFXButton.ButtonType.RAISED) {
                            action {
                                SipProtocol.resetFactory()
                                controller.formRequestFragmentList.clear()
                                fire(ClearFormRequestEvent())
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
                            graphic = hbox {
                                val iconOne =
                                        if (!it.model.isSendingRequest.value)
                                            FontAwesomeIconView(FontAwesomeIcon.ARROW_RIGHT)
                                        else
                                            FontAwesomeIconView(FontAwesomeIcon.ARROW_LEFT)
                                iconOne.glyphSize = 13
                                val iconTwo = FontAwesomeIconView(FontAwesomeIcon.DESKTOP)
                                iconTwo.glyphSize = 13
                                hbox {
                                    style {
                                        padding = box(0.px, 10.px, 0.px, 0.px)
                                        spacing = 5.px
                                        alignment = Pos.CENTER
                                    }
                                    add(iconOne)
                                    add(iconTwo)
                                }
                                vbox {
                                    spacing = 1.0
                                    val size = if (it.model.formRequest.method == RequestEnum.ACK) 12.px else 18.px
                                    label(it.model.formRequest.method.toString()) {
                                        style {
                                            fontSize = size
                                        }
                                    }
                                    if (it.model.formRequest.method == RequestEnum.ACK) {
                                        label("${it.model.formRequest.statusCode}(${it.model.formRequest.messageStatusCode})") {
                                            style {
                                                fontSize = size
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        onUserSelect(clickCount = 1) {
                            fire(OpenFormRequestEvent(controller.formRequestFragmentList.indexOf(it)))
                        }
                        subscribe<RefreshListFormRequestEvent> {
                            refresh()
                        }
                        subscribe<SelectElementInListEvent> {
                            this@jfxlistview.selectionModel.select(it.index)
                        }
                        vboxConstraints {
                            vgrow = Priority.ALWAYS
                        }
                    }
                }
            }
        }
    }


    class OpenFormRequestEvent(val selectedIndex: Int) : FXEvent()

    class RefreshListFormRequestEvent : FXEvent()

    class ClearFormRequestEvent : FXEvent()

    class SelectElementInListEvent(val index: Int) : FXEvent()
}
