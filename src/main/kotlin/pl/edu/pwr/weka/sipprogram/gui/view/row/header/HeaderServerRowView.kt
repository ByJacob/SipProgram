package pl.edu.pwr.weka.sipprogram.gui.view.row.header

import kfoenix.jfxtextfield
import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderCallIdController
import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderServerController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderCallIdModel
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderServerModel
import pl.edu.pwr.weka.sipprogram.gui.view.row.base.BaseHeaderView
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 16:35
 */
class HeaderServerRowView : BaseHeaderView("Server") {
    override val controller: HeaderServerController by inject()

    override val root = form {
        fieldset("Server") {
            field("Server") {
                jfxtextfield {
                    bind(controller.model.server)
                }
            }
        }
    }

}
