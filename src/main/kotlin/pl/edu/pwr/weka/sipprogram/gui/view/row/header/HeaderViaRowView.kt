package pl.edu.pwr.weka.sipprogram.gui.view.row.header

import javafx.collections.FXCollections
import kfoenix.jfxcombobox
import kfoenix.jfxtextfield
import pl.edu.pwr.weka.sipprogram.gui.controller.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderViaController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderViaModel
import pl.edu.pwr.weka.sipprogram.gui.view.row.base.BaseHeaderView
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.TransportProtocol
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 16:20
 */
class HeaderViaRowView : BaseHeaderView("Via") {
    override val controller: HeaderViaController by inject()

    override val root = form {
        fieldset("Via") {
            field("Protokół") {
                jfxcombobox<TransportProtocol> {
                    items = FXCollections.observableArrayList(TransportProtocol.values().toList())
                    bind(controller.model.protocol)
                }
            }
            field("Wysłane z adresu") {
                jfxtextfield {
                    bind(controller.model.address)
                    isDisable = true
                }
            }
            field("Wysłane z portu") {
                jfxtextfield {
                    bind(controller.model.port)
                    isDisable = true
                }
            }
            field("Branch") {
                jfxtextfield {
                    bind(controller.model.branch)
                }
            }
        }
    }

}
