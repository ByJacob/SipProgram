package pl.edu.pwr.weka.sipprogram.gui.view.header

import javafx.collections.FXCollections
import kfoenix.jfxcombobox
import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderAuthenticationController
import pl.edu.pwr.weka.sipprogram.gui.view.header.base.BaseHeaderView
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.AlgorithmEnum
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.AuthSchemeEnum
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.RequestEnum
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 17:23
 */
class HeaderAuthorizationRowView : BaseHeaderView("Authorizatio") {
    override val controller: HeaderAuthenticationController by inject(Scope())

    override val root = form {
        fieldset("Authorization") {
            field("Username") {
                add(createTextFieldWithHelp(controller.model.username))
            }
            field("Password") {
                add(createTextFieldWithHelp(controller.model.password))
            }
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
            field("Metoda") {
                jfxcombobox<RequestEnum> {
                    items = FXCollections.observableArrayList(RequestEnum.values().toList())
                    bind(controller.model.method)
                }
            }
            field("Authentication URI Host") {
                add(createTextFieldWithHelp(controller.model.authenticationUriHost))
            }
            field("Authentication URI Port") {
                add(createTextFieldWithHelp(controller.model.authenticationUriPort))
            }
        }
    }
}