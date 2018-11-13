package pl.edu.pwr.weka.sipprogram.gui.view.row.header

import com.jfoenix.controls.JFXChipView
import javafx.collections.ListChangeListener
import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderAllowController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderAllowModel
import pl.edu.pwr.weka.sipprogram.gui.view.row.base.BaseHeaderView
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.RequestEnum
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 16:35
 */
class HeaderAllowRowView : BaseHeaderView("Allow") {
    override val model = HeaderAllowModel()
    override val controller: HeaderAllowController by inject()

    init {
        controller.model = model
    }

    override val root = form {
        fieldset("Allow") {
            field("Allow") {
                val chipView = JFXChipView<RequestEnum>()
                chipView.suggestions.addAll(RequestEnum.values())
                chipView.chips.bind(model.allowList.value) { request -> request}
                add(chipView)
            }
        }
    }

}
