package pl.edu.pwr.weka.sipprogram.gui.view.row.header

import javafx.collections.FXCollections
import kfoenix.jfxcombobox
import kfoenix.jfxtextfield
import pl.edu.pwr.weka.sipprogram.gui.controller.base.HeaderWWWAuthenticateController
import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderRequestLineController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderRequestLineModel
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderWWWAuthenticateModel
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
class HeaderWWWAuthenticateRowView : BaseHeaderView("WWW-Authenticate") {
    override val controller: HeaderWWWAuthenticateController by inject()

    override val model = HeaderWWWAuthenticateModel()

    init {
        controller.model = model
    }

    override val root = form {
        fieldset("WWW-Authenticate") {
            field("Authentication Scheme") {
                jfxcombobox<AuthSchemeEnum> {
                    items = FXCollections.observableArrayList(AuthSchemeEnum.values().toList())
                    bind(model.scheme)
                }
            }
            field("Algorithm") {
                jfxcombobox<AlgorithmEnum> {
                    items = FXCollections.observableArrayList(AlgorithmEnum.values().toList())
                    bind(model.algorithm)
                }
            }
            field("Realm") {
                jfxtextfield {
                    bind(model.realm)
                    clear()
                }
            }
            field("Nonce") {
                jfxtextfield {
                    bind(model.nonce)
                }
            }
        }
    }
}