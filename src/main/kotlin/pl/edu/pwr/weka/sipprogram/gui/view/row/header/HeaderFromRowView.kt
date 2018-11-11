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
    override val model = HeaderFromModel()
    override val controller: HeaderFromController by inject()

    init {
        controller.model = model
    }

    override val root = form {
        fieldset("From") {
            field("Nazwa użytkownika") {
                jfxtextfield {
                    bind(model.user)
                    clear()
                }
            }
            field("Adres użytkownika") {
                jfxtextfield {
                    bind(model.address, true)
                    isDisable = true
                }
            }
            field("Port użytkownika") {
                jfxtextfield {
                    bind(model.port, true)
                    isDisable = true
                }
            }
            field("Tag") {
                jfxtextfield {
                    bind(model.tag)
                }
            }
        }
    }

}
