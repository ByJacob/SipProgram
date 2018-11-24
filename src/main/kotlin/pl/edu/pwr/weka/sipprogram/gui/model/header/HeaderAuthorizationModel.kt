package pl.edu.pwr.weka.sipprogram.gui.model.header

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.AlgorithmEnum
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.AuthSchemeEnum
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.RequestEnum
import pl.edu.pwr.weka.sipprogram.util.Generators
import tornadofx.*
import tornadofx.getValue
import tornadofx.setValue

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 13.11.2018 22:35
 */

class HeaderAuthorization {
    val schemeProperty = SimpleObjectProperty<AuthSchemeEnum>(AuthSchemeEnum.DIGEST)
    var scheme by schemeProperty

    val algorithmProperty = SimpleObjectProperty<AlgorithmEnum>(AlgorithmEnum.MD5)
    var algorithm by algorithmProperty

    val realmProperty = SimpleStringProperty("")
    var realm by realmProperty

    val nonceProperty = SimpleStringProperty("")
    var nonce by nonceProperty

    val usernameProperty = SimpleStringProperty()
    var username by usernameProperty

    val passwordProperty = SimpleStringProperty()
    var password by passwordProperty

    val methodProperty = SimpleObjectProperty<RequestEnum>(RequestEnum.REGISTER)
    var method by methodProperty

    val authenticationUriHostProperty = SimpleStringProperty("")
    var authenticationUriHost by authenticationUriHostProperty

    val authenticationUriPortProperty = SimpleIntegerProperty(0)
    var authenticationUriPort by authenticationUriPortProperty


}

class HeaderAuthorizationModel (val headerAuthorization: HeaderAuthorization): ViewModel() {
    constructor(): this(HeaderAuthorization())
    val scheme = bind(true){headerAuthorization.schemeProperty}
    val algorithm = bind(true){headerAuthorization.algorithmProperty}
    val realm = bind(true){headerAuthorization.realmProperty}
    val nonce = bind(true){headerAuthorization.nonceProperty}
    val username = bind(true){headerAuthorization.usernameProperty}
    val password = bind(true){headerAuthorization.passwordProperty}
    val method = bind(true){headerAuthorization.methodProperty}
    val authenticationUriHost = bind(true){headerAuthorization.authenticationUriHostProperty}
    val authenticationUriPort = bind(true){headerAuthorization.authenticationUriPortProperty}
}