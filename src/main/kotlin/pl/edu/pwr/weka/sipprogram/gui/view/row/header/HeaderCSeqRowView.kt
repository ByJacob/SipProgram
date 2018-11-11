package pl.edu.pwr.weka.sipprogram.gui.view.row.header

import javafx.collections.FXCollections
import kfoenix.jfxcombobox
import kfoenix.jfxtextfield
import pl.edu.pwr.weka.sipprogram.gui.controller.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderCSeqController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderCSeqModel
import pl.edu.pwr.weka.sipprogram.gui.view.row.base.BaseHeaderView
import pl.edu.pwr.weka.sipprogram.sip.request.base.RequestEnum
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 16:35
 */
class HeaderCSeqRowView : BaseHeaderView("CSeq") {
    override val model = HeaderCSeqModel()
    override val controller: HeaderCSeqController by inject()
    override val root = form {
        fieldset("CSeq") {
            field("Numer") {
                jfxtextfield {
                    promptText = "1234"
                }
            }
            field("Metoda") {
                jfxcombobox<RequestEnum> {
                    items = FXCollections.observableArrayList(RequestEnum.values().toList())
                }
            }
        }
    }

}
