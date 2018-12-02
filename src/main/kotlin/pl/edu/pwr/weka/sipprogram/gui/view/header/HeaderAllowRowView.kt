package pl.edu.pwr.weka.sipprogram.gui.view.header

import com.jfoenix.controls.JFXChipView
import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderAllowController
import pl.edu.pwr.weka.sipprogram.gui.view.header.base.BaseHeaderView
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.RequestEnum
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 16:35
 */
class HeaderAllowRowView : BaseHeaderView("Allow") {
    override val controller: HeaderAllowController by inject(Scope())

    override val root = form {
        fieldset("Allow") {
            field {
                vbox {
                    val chipView = JFXChipView<RequestEnum>()
                    chipView.suggestions.addAll(RequestEnum.values())
                    chipView.chips.bind(controller.model.allowList.value) { request -> request }
                    add(chipView)
                }
            }

        }
    }
}
