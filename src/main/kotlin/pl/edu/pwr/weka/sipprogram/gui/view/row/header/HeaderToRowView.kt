package pl.edu.pwr.weka.sipprogram.gui.view.row.header

import kfoenix.jfxtextfield
import pl.edu.pwr.weka.sipprogram.gui.controller.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderToController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderToModel
import pl.edu.pwr.weka.sipprogram.gui.view.row.base.BaseHeaderView
import tornadofx.*
import javax.sip.header.Header

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 16:35
 */
class HeaderToRowView : BaseHeaderView("To") {
    override val controller: HeaderToController by inject()

    override val root = form {
        fieldset("To") {
            field("Nazwa użytkownika") {
                jfxtextfield {
                    bind(controller.model.user)
                    clear()
                }
            }
            field("Adres użytkownika") {
                jfxtextfield {
                    bind(controller.model.address)
                    clear()
                }
            }
            field("Port użytkownika") {
                jfxtextfield {
                    bind(controller.model.port)
                    clear()
                }
            }
            field("Tag") {
                jfxtextfield {
                    bind(controller.model.tag)
                }
            }
        }
    }

}
