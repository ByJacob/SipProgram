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
    override val model = HeaderAllowModel()
    override val controller: HeaderAllowController by inject()

    init {
        controller.model = model
    }

    override val root = form {
        fieldset("Allow") {
            field {
                vbox {
                    val chipView = JFXChipView<RequestEnum>()
                    chipView.suggestions.addAll(RequestEnum.values())
                    chipView.chips.addListener { c: ListChangeListener.Change<*> ->
                        while (c.next()) {
                            c.removed.forEach { model.allowList.remove(it) }
                            c.addedSubList.forEach {
                                when (it) {
                                    is RequestEnum -> {
                                        if (model.allowList.indexOf(it)<0)
                                            model.allowList.add(it)
                                        else {
                                            chipView.chips.remove(it as Any)
                                        }
                                    }
                                    else -> {
                                        chipView.chips.remove(it)
                                        val msg = "To nie jest poprawna wartość: $it.\n" +
                                                "Możliwe wartości: " + RequestEnum.values()
                                                .joinToString { itList -> itList.sipName }
                                        Toast.makeText(msg, 1500, 500, 500)
                                    }
                                }
                            }
                        }
                    }
                    add(chipView)
                }
            }

        }
    }
}
