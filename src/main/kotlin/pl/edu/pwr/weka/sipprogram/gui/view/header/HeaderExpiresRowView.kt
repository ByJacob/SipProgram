package pl.edu.pwr.weka.sipprogram.gui.view.header

import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderExpiresController
import pl.edu.pwr.weka.sipprogram.gui.view.header.base.BaseHeaderView
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 16:35
 */
class HeaderExpiresRowView : BaseHeaderView("Expires") {
    override val controller: HeaderExpiresController by inject(Scope())

    override val root = form {
        fieldset("Expires") {
            field("Expires") {
                add(createTextFieldWithHelp(controller.model.expires))
            }
        }
    }

}
