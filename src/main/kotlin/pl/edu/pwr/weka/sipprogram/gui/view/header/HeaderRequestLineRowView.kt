package pl.edu.pwr.weka.sipprogram.gui.view.header

import javafx.beans.property.SimpleStringProperty
import kfoenix.jfxcombobox
import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderRequestLineController
import pl.edu.pwr.weka.sipprogram.gui.view.header.base.BaseHeaderView
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.RequestEnum
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 17:23
 */
class HeaderRequestLineRowView : BaseHeaderView("Request-Line") {
    override val controller: HeaderRequestLineController by inject(Scope())

    val helpRequest = SimpleStringProperty("Rodzaj zapytania jaki chcesz wysłać.")

    override val root = form {
        fieldset("Request-Line") {
            field("Metoda") {
                add(createVomboBoxWithHelp<RequestEnum>(controller.requestMethod, controller.model.method, helpRequest))

            }
            field("Request-URI Host") {
                add(createTextFieldWithHelp(controller.model.requestHost))
            }
            field("Request-URI Port") {
                add(createTextFieldWithHelp(controller.model.requestPort))
            }
        }
    }
}