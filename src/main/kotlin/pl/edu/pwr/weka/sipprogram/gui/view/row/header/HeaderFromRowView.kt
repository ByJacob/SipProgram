package pl.edu.pwr.weka.sipprogram.gui.view.row.header

import kfoenix.jfxtextfield
import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderFromController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderFromModel
import pl.edu.pwr.weka.sipprogram.gui.view.row.base.BaseHeaderView
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 16:35
 */
class HeaderFromRowView : BaseHeaderView("From") {
    override val controller: HeaderFromController by inject()

    override val root = form {
        fieldset("From") {
            field("Nazwa użytkownika") {
                jfxtextfield {
                    bind(controller.model.user)
                    clear()
                }
            }
            field("Adres użytkownika") {
                jfxtextfield {
                    bind(controller.model.address, true)
                    isDisable = true
                }
            }
            field("Port użytkownika") {
                jfxtextfield {
                    bind(controller.model.port, true)
                    isDisable = true
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
