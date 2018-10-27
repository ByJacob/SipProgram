package pl.edu.pwr.weka.sipprogram.gui2.view

import com.jfoenix.controls.JFXButton
import javafx.geometry.Pos
import kfoenix.jfxbutton
import kfoenix.jfxtextfield
import pl.edu.pwr.weka.sipprogram.gui2.controller.ProcessConnectionController
import tornadofx.*

class ProcessConnectionView : View("ProcessConnection") {
    val controller: ProcessConnectionController by inject()
    override val root = borderpane()

    init {
        with(root) {
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
                            controller.createRequestForm
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
            }
            center {hbox {  }}
        }
    }

}
