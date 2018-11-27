package pl.edu.pwr.weka.sipprogram.gui.view.row.header

import kfoenix.jfxtextfield
import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderExpiresController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderExpiresModel
import pl.edu.pwr.weka.sipprogram.gui.view.row.base.BaseHeaderView
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
                jfxtextfield {
                    bind(controller.model.expires)
                    clear()
                }
            }
        }
    }

}
