package pl.edu.pwr.weka.sipprogram.gui.view.header

import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderFromController
import pl.edu.pwr.weka.sipprogram.gui.view.header.base.BaseHeaderView
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 16:35
 */
class HeaderFromRowView : BaseHeaderView("From") {
    override val controller: HeaderFromController by inject(Scope())

    override val root = form {
        fieldset("From") {
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
