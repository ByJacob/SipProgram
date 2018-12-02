package pl.edu.pwr.weka.sipprogram.gui.view.header

import kfoenix.jfxcombobox
import kfoenix.jfxtextfield
import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderRequestLineController
import pl.edu.pwr.weka.sipprogram.gui.view.header.base.BaseHeaderView
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.RequestEnum
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 17:23
 */
class HeaderRequestLineRowView : BaseHeaderView("Request-Line") {
    override val controller: HeaderRequestLineController by inject(Scope())

    override val root = form {
        fieldset("Request-Line") {
            field("Metoda") {
                jfxcombobox<RequestEnum> {
                    items = controller.requestMethod
                    bind(controller.model.method)
                }
            }
            field("Request-URI Host") {
                jfxtextfield {
                    bind(controller.model.requestHost)
                    clear()
                }
            }
            field("Request-URI Port") {
                jfxtextfield {
                    bind(controller.model.requestPort)
                    clear()
                }
            }
        }
    }
}