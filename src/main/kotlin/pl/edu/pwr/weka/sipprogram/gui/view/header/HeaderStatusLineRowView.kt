package pl.edu.pwr.weka.sipprogram.gui.view.header

import javafx.collections.FXCollections
import kfoenix.jfxcombobox
import kfoenix.jfxtextfield
import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderStatusLineController
import pl.edu.pwr.weka.sipprogram.gui.view.header.base.BaseHeaderView
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.RequestEnum
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 17:23
 */
class HeaderStatusLineRowView : BaseHeaderView("Status-Line") {
    override val controller: HeaderStatusLineController by inject(Scope())

    override val root = form {
        fieldset("Status-Line") {
            field("Metoda") {
                jfxcombobox<RequestEnum> {
                    items = FXCollections.observableArrayList(RequestEnum.ACK)
                    bind(controller.model.method)
                }
            }
            field("Status-Code") {
                jfxtextfield {
                    bind(controller.model.statusCode)
                    clear()
                }
            }
            field("Message") {
                jfxtextfield {
                    bind(controller.model.message)
                    clear()
                }
            }
        }
    }
}