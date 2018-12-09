package pl.edu.pwr.weka.sipprogram.gui.view.header

import javafx.collections.FXCollections
import kfoenix.jfxcombobox
import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderCSeqController
import pl.edu.pwr.weka.sipprogram.gui.view.header.base.BaseHeaderView
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.RequestEnum
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 16:35
 */
class HeaderCSeqRowView : BaseHeaderView("CSeq") {
    override val controller: HeaderCSeqController by inject(Scope())

    override val root = form {
        fieldset("CSeq") {
            field("Numer") {
                add(createMaterialTextField(controller.model.number))
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
