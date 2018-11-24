package pl.edu.pwr.weka.sipprogram.gui.view.row.header

import javafx.collections.FXCollections
import kfoenix.jfxcombobox
import kfoenix.jfxtextfield
import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderCSeqController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderCSeqModel
import pl.edu.pwr.weka.sipprogram.gui.view.row.base.BaseHeaderView
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.RequestEnum
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 16:35
 */
class HeaderCSeqRowView : BaseHeaderView("CSeq") {
    override val controller: HeaderCSeqController by inject()

    override val root = form {
        fieldset("CSeq") {
            field("Numer") {
                jfxtextfield {
                    bind(controller.model.number)
                    clear()
                }
            }
            field("Metoda") {
                jfxcombobox<RequestEnum> {
                    items = FXCollections.observableArrayList(RequestEnum.values().toList())
                    bind(controller.model.method)
                }
            }
        }
    }

}
