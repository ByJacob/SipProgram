package pl.edu.pwr.weka.sipprogram.gui.view.header

import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderToController
import pl.edu.pwr.weka.sipprogram.gui.view.header.base.BaseHeaderView
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 16:35
 */
class HeaderToRowView : BaseHeaderView("To") {
    override val controller: HeaderToController by inject(Scope())

    override val root = form {
        fieldset("To") {
            field("Nazwa użytkownika") {
                add(createTextFieldWithHelp(controller.model.user))
            }
            field("Adres użytkownika") {
                add(createTextFieldWithHelp(controller.model.address))
            }
            field("Port użytkownika") {
                add(createTextFieldWithHelp(controller.model.port))
            }
            field("Tag") {
                add(createTextFieldWithHelp(controller.model.tag))
            }
        }
    }

}
