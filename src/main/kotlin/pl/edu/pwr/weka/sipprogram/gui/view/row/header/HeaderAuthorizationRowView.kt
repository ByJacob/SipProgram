package pl.edu.pwr.weka.sipprogram.gui.view.row.header

import javafx.collections.FXCollections
import kfoenix.jfxcombobox
import kfoenix.jfxtextfield
import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderAuthenticationController
import pl.edu.pwr.weka.sipprogram.gui.view.row.base.BaseHeaderView
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
                jfxtextfield {
                    bind(controller.model.username)
                }
            }
            field("Password") {
                jfxtextfield {
                    bind(controller.model.password)
                }
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
                jfxtextfield {
                    bind(controller.model.realm)
                    clear()
                }
            }
            field("Nonce") {
                jfxtextfield {
                    bind(controller.model.nonce)
                }
            }
            field("Metoda") {
                jfxcombobox<RequestEnum> {
                    items = FXCollections.observableArrayList(RequestEnum.values().toList())
                    bind(controller.model.method)
                }
            }
            field("Authentication URI Host") {
                jfxtextfield {
                    bind(controller.model.authenticationUriHost)
                }
            }
            field("Authentication URI Port") {
                jfxtextfield {
                    bind(controller.model.authenticationUriPort)
                    clear()
                }
            }
        }
    }
}