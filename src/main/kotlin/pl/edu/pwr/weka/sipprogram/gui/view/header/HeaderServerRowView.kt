package pl.edu.pwr.weka.sipprogram.gui.view.header

import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderServerController
import pl.edu.pwr.weka.sipprogram.gui.view.header.base.BaseHeaderView
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 16:35
 */
class HeaderServerRowView : BaseHeaderView("Server") {
    override val controller: HeaderServerController by inject(Scope())

    override val root = form {
        fieldset("Server") {
            field("Server") {
                add(createMaterialTextField(controller.model.server))
            }
        }
    }

}
