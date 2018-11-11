package pl.edu.pwr.weka.sipprogram.gui.view.row.header

import javafx.collections.FXCollections
import kfoenix.jfxcombobox
import kfoenix.jfxtextfield
import pl.edu.pwr.weka.sipprogram.gui.controller.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderExpiresController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderExpiresModel
import pl.edu.pwr.weka.sipprogram.gui.view.row.base.BaseHeaderView
import pl.edu.pwr.weka.sipprogram.sip.request.base.RequestEnum
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 16:35
 */
class HeaderExpiresRowView : BaseHeaderView("Expires") {
    override val model = HeaderExpiresModel()
    override val controller: HeaderExpiresController by inject()

    init {
        controller.model = model
    }

    override val root = form {
        fieldset("Expires") {
            field("Expires") {
                jfxtextfield {
                    bind(model.expires)
                    clear()
                }
            }
        }
    }

}
