package pl.edu.pwr.weka.sipprogram.gui.view.header

import javafx.collections.FXCollections
import kfoenix.jfxcombobox
import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderViaController
import pl.edu.pwr.weka.sipprogram.gui.view.header.base.BaseHeaderView
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.TransportProtocol
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 16:20
 */
class HeaderViaRowView : BaseHeaderView("Via") {
    override val controller: HeaderViaController by inject(Scope())

    override val root = form {
        fieldset("Via") {
            field("Protokół") {
                jfxcombobox<TransportProtocol> {
                    items = FXCollections.observableArrayList(TransportProtocol.values().toList())
                    bind(controller.model.protocol)
                }
            }
            field("Wysłane z adresu") {
                add(createTextFieldWithHelp(controller.model.address))
            }
            field("Wysłane z portu") {
                add(createTextFieldWithHelp(controller.model.port))
            }
            field("Branch") {
                add(createTextFieldWithHelp(controller.model.branch))
            }
        }
    }

}
