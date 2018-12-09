package pl.edu.pwr.weka.sipprogram.gui.view.header

import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderCallIdController
import pl.edu.pwr.weka.sipprogram.gui.view.header.base.BaseHeaderView
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 16:35
 */
class HeaderCallIdRowView : BaseHeaderView("Call-ID") {
    override val controller: HeaderCallIdController by inject(Scope())

    override val root = form {
        fieldset("Call-ID") {
            field("Call-ID") {
                add(createTextFieldWithHelp(controller.model.callId))
            }
        }
    }

}
