package pl.edu.pwr.weka.sipprogram.gui.view

import com.jfoenix.controls.JFXButton
import javafx.geometry.Pos
import javafx.scene.control.ScrollPane
import javafx.scene.layout.Priority
import kfoenix.jfxbutton
import kfoenix.jfxlistview
import pl.edu.pwr.weka.sipprogram.gui.controller.ProcessConnectionController
import pl.edu.pwr.weka.sipprogram.gui.model.ProcessConnectionModel
import pl.edu.pwr.weka.sipprogram.gui.view.row.ProcessConnectionItemView
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import tornadofx.*

class ProcessConnectionView : View(FX.messages["simulation"]) {
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
                            graphic = ProcessConnectionItemView(it).root
                        }
                        onUserSelect(clickCount = 1) {
                            fire(OpenFormRequestEvent(controller.formRequestFragmentList.indexOf(it)))
                        }
                        subscribe<RefreshListFormRequestEvent> {
                            refresh()
                        }
                        subscribe<SelectElementInListEvent> {
                            when {
                                this@jfxlistview.items.size <= 0 -> fire(ClearFormRequestEvent())
                                it.index >= this@jfxlistview.items.size ->
                                    this@jfxlistview.selectionModel.select(this@jfxlistview.items.lastIndex)
                                else -> this@jfxlistview.selectionModel.select(it.index)
                            }
                        }
                        vboxConstraints {
                            vgrow = Priority.ALWAYS
                        }
                    }
                }
            }
        }
    }


    class OpenFormRequestEvent(var selectedIndex: Int) : FXEvent()

    class RefreshListFormRequestEvent : FXEvent()

    class ClearFormRequestEvent : FXEvent()

    class SelectElementInListEvent(val index: Int) : FXEvent()
}
