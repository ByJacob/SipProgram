package pl.edu.pwr.weka.sipprogram.gui.view.header

import javafx.collections.FXCollections
import kfoenix.jfxcombobox
import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderWWWAuthenticateController
import pl.edu.pwr.weka.sipprogram.gui.view.header.base.BaseHeaderView
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.AlgorithmEnum
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.AuthSchemeEnum
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 17:23
 */
class HeaderWWWAuthenticateRowView : BaseHeaderView("WWW-Authenticate") {
    override val controller: HeaderWWWAuthenticateController by inject(Scope())

    override val root = form {
        fieldset("WWW-Authenticate") {
            field("Authentication Scheme") {
                jfxcombobox<AuthSchemeEnum> {
                    items = FXCollections.observableArrayList(AuthSchemeEnum.values().toList())
                    bind(controller.model.scheme)
                }
            }
            field("Algorithm") {
                jfxcombobox<AlgorithmEnum> {
                    items = FXCollections.observableArrayList(AlgorithmEnum.values().toList())
                    bind(controller.model.algorithm)
                }
            }
            field("Realm") {
                add(createTextFieldWithHelp(controller.model.realm))
            }
            field("Nonce") {
                add(createTextFieldWithHelp(controller.model.nonce))
            }
        }
    }
}