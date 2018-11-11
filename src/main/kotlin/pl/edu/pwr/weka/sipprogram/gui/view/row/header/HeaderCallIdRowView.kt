package pl.edu.pwr.weka.sipprogram.gui.view.row.header

import kfoenix.jfxtextfield
import pl.edu.pwr.weka.sipprogram.gui.controller.base.HeaderCallIdController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderCallIdModel
import pl.edu.pwr.weka.sipprogram.gui.view.row.base.BaseHeaderView
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 16:35
 */
class HeaderCallIdRowView : BaseHeaderView("Call-ID") {
    override val model = HeaderCallIdModel()
    override val controller: HeaderCallIdController by inject()
    override val root = form {
        fieldset("Call-ID") {
            field("Call-ID") {
                jfxtextfield {
                    promptText = "1234567890abcdef"
                }
            }
        }
    }

}
