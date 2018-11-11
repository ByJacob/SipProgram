package pl.edu.pwr.weka.sipprogram.gui.view.row.header

import kfoenix.jfxtextfield
import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderContactController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderContactModel
import pl.edu.pwr.weka.sipprogram.gui.view.row.base.BaseHeaderView
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 16:35
 */
class HeaderContactRowView : BaseHeaderView("Contact") {
    override val model = HeaderContactModel()
    override val controller: HeaderContactController by inject()
    override val root = form {
        fieldset("Contact") {
            field("Nazwa użytkownika") {
                jfxtextfield {
                    promptText = "użytkownik"
                }
            }
            field("Adres użytkownika") {
                jfxtextfield {
                    promptText = "70"
                }
            }
            field("Port użytkownika") {
                jfxtextfield {
                    promptText = "70"
                }
            }
        }
    }

}
