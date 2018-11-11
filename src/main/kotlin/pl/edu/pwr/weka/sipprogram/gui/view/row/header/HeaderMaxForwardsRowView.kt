package pl.edu.pwr.weka.sipprogram.gui.view.row.header

import kfoenix.jfxtextfield
import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderMaxForwardsController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderMaxForwardsModel
import pl.edu.pwr.weka.sipprogram.gui.view.row.base.BaseHeaderView
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 16:35
 */
class HeaderMaxForwardsRowView : BaseHeaderView("Max-Forwards") {
    override val model = HeaderMaxForwardsModel()
    override val controller: HeaderMaxForwardsController by inject()

    init {
        controller.model = model
    }

    override val root = form {
        fieldset("Max-Forwards") {
            field("Max Forwards") {
                jfxtextfield {
                    bind(model.maxForwards)
                    clear()
                }
            }
        }
    }

}
