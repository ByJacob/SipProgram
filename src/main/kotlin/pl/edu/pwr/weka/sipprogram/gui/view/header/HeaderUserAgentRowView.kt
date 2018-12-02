package pl.edu.pwr.weka.sipprogram.gui.view.header

import kfoenix.jfxtextfield
import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderUserAgentController
import pl.edu.pwr.weka.sipprogram.gui.view.header.base.BaseHeaderView
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 16:35
 */
class HeaderUserAgentRowView() : BaseHeaderView("User-Agent") {
    override val controller: HeaderUserAgentController by inject(Scope())

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
