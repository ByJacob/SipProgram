package pl.edu.pwr.weka.sipprogram.gui.view.header

import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderMaxForwardsController
import pl.edu.pwr.weka.sipprogram.gui.view.header.base.BaseHeaderView
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 16:35
 */
class HeaderMaxForwardsRowView : BaseHeaderView("Max-Forwards") {
    override val controller: HeaderMaxForwardsController by inject(Scope())

    override val root = form {
        fieldset("Max-Forwards") {
            field("Max Forwards") {
                add(createMaterialTextField(controller.model.maxForwards))
            }
        }
    }

}
