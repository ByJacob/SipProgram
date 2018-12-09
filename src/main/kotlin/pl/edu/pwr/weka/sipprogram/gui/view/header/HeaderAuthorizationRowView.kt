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
                add(createVomboBoxWithHelp<AuthSchemeEnum>(FXCollections.observableArrayList(AuthSchemeEnum.values().toList()), controller.model.scheme))
            }
            field("Algorithm") {
                add(createVomboBoxWithHelp<AlgorithmEnum>(FXCollections.observableArrayList(AlgorithmEnum.values().toList()), controller.model.algorithm))
            }
            field("Realm") {
                add(createTextFieldWithHelp(controller.model.realm))
            }
            field("Nonce") {
                add(createTextFieldWithHelp(controller.model.nonce))
            }
            field("Metoda") {
                add(createVomboBoxWithHelp<RequestEnum>(FXCollections.observableArrayList(RequestEnum.values().toList()), controller.model.method))
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