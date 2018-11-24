package pl.edu.pwr.weka.sipprogram.gui.view.row.header

import com.jfoenix.controls.JFXChipView
import javafx.collections.ListChangeListener
import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderAllowController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderAllowModel
import pl.edu.pwr.weka.sipprogram.gui.view.row.base.BaseHeaderView
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.RequestEnum
import pl.edu.pwr.weka.sipprogram.util.Toast
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 16:35
 */
class HeaderAllowRowView : BaseHeaderView("Allow") {
    override val controller: HeaderAllowController by inject()


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
