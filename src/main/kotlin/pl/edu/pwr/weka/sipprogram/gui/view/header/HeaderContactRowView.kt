package pl.edu.pwr.weka.sipprogram.gui.view.header

import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderContactController
import pl.edu.pwr.weka.sipprogram.gui.view.header.base.BaseHeaderView
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 16:35
 */
class HeaderContactRowView : BaseHeaderView("Contact") {
    override val controller: HeaderContactController by inject(Scope())

    override val root = form {
        fieldset("Contact") {
            field("Nazwa użytkownika") {
                add(createMaterialTextField(controller.model.user))
            }
            field("Adres użytkownika") {
                add(createMaterialTextField(controller.model.address))
            }
            field("Port użytkownika") {
                add(createMaterialTextField(controller.model.port))
            }
            field("Expires") {
                add(createMaterialTextField(controller.model.expires))
            }
        }
    }

}
