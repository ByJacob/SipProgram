package pl.edu.pwr.weka.sipprogram.gui.view.row.header

import kfoenix.jfxtextfield
import pl.edu.pwr.weka.sipprogram.gui.controller.base.BaseHeaderController
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
    override val root = form {
        fieldset("From") {
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
            field("Tag") {
                jfxtextfield {
                    promptText = "1234567890abcdef"
                }
            }
        }
    }

}
