package pl.edu.pwr.weka.sipprogram.gui.view.row.header

import javafx.collections.FXCollections
import kfoenix.jfxcombobox
import kfoenix.jfxtextfield
import pl.edu.pwr.weka.sipprogram.gui.controller.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderRequestLineController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderRequestLineRowModel
import pl.edu.pwr.weka.sipprogram.gui.view.row.base.BaseHeaderView
import pl.edu.pwr.weka.sipprogram.sip.request.base.RequestEnum
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 17:23
 */
class HeaderRequestLineRowView : BaseHeaderView("Request-Line") {
    override val controller: HeaderRequestLineController by inject()

    override val model = HeaderRequestLineRowModel()

    override val root = form {
        fieldset("Request-Line") {
            field("Metoda") {
                jfxcombobox<RequestEnum> {
                    items = FXCollections.observableArrayList(RequestEnum.values().toList())
                    bind(model.method)
                }
            }
            field("Request-URI Host") {
                jfxtextfield {
                    promptText = "192.168.1.108"
                    bind(model.requestHost)
                    clear()
                }
            }
            field("Request-URI Port") {
                jfxtextfield {
                    promptText = "8080"
                    bind(model.requestPort)
                    clear()
                }
            }
        }
    }
}