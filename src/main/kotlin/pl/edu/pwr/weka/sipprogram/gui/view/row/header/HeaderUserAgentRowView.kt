package pl.edu.pwr.weka.sipprogram.gui.view.row.header

import kfoenix.jfxtextfield
import pl.edu.pwr.weka.sipprogram.gui.controller.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderUserAgentController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderUserAgentModel
import pl.edu.pwr.weka.sipprogram.gui.view.row.base.BaseHeaderView
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 16:35
 */
class HeaderUserAgentRowView() : BaseHeaderView("User-Agent") {
    override val controller: HeaderUserAgentController by inject()

    override val root = form {
        fieldset("User-Agent") {
            field("User-Agent") {
                jfxtextfield {
                    bind(controller.model.userAgent)
                    clear()
                }
            }
        }
    }

}
