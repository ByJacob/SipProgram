package pl.edu.pwr.weka.sipprogram.gui.view.row.header

import com.jfoenix.controls.JFXChipView
import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderSupportedController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderSupportedModel
import pl.edu.pwr.weka.sipprogram.gui.view.row.base.BaseHeaderView
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.SupportedEnum
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 16:35
 */
class HeaderSupportedRowView : BaseHeaderView("Supported") {
    override val controller: HeaderSupportedController by inject()

    override val root = form {
        fieldset("Supported") {
            field {
                vbox {
                    val chipView = JFXChipView<SupportedEnum>()
                    chipView.suggestions.addAll(SupportedEnum.values())
                    chipView.chips.bind(controller.model.supportedList.value) { request -> request }
                    add(chipView)
                }
            }
        }
    }

}
