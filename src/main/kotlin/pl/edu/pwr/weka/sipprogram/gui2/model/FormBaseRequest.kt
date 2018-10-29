package pl.edu.pwr.weka.sipprogram.gui2.model

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import pl.edu.pwr.weka.sipprogram.sip.auth.enums.AlgorithmEnum
import pl.edu.pwr.weka.sipprogram.sip.auth.enums.AuthMethodEnum
import pl.edu.pwr.weka.sipprogram.sip.auth.enums.QualityOfProtectionEnum
import pl.edu.pwr.weka.sipprogram.sip.request.base.RequestEnum
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 27.10.2018 16:20
 */

class FormBaseRequest {

    val requestProperty = SimpleObjectProperty<RequestEnum>(RequestEnum.REGISTER)
    var request by requestProperty

    val localAddressProperty = SimpleStringProperty("")
    var localAddress by localAddressProperty

    val localPortProperty = SimpleIntegerProperty(8080)
    var localPort by localPortProperty

    val serverAddressProperty = SimpleStringProperty("")
    var serverAddress by serverAddressProperty

    val serverPortProperty = SimpleIntegerProperty(5160)
    var serverPort by serverPortProperty

    val userProperty = SimpleStringProperty("")
    var user by userProperty

    val passwordProperty = SimpleStringProperty("")
    var password by passwordProperty

    val callIdProperty = SimpleStringProperty("")
    var callId by callIdProperty

    val seqNumberProperty = SimpleIntegerProperty(0)
    var seqNumber by seqNumberProperty

    val requestTxtSpringProperty = SimpleStringProperty("")
    var requestTxtSpring by requestTxtSpringProperty
    //auth
    val authorizationEnabledProperty = SimpleBooleanProperty(false)
    var authorizationEnabled by authorizationEnabledProperty

    val typeAuthorizationProperty = SimpleObjectProperty<AuthMethodEnum>(AuthMethodEnum.DIGEST)
    var typeAuthorization by typeAuthorizationProperty

    val realmNameProperty = SimpleStringProperty("")
    var realmName by realmNameProperty

    val algorithmProperty = SimpleObjectProperty<AlgorithmEnum>(AlgorithmEnum.MD5)
    var algorithm by algorithmProperty

    val nonceProperty = SimpleStringProperty("")
    var nonce by nonceProperty

    val qopProperty = SimpleObjectProperty<QualityOfProtectionEnum>(QualityOfProtectionEnum.EMPTY)
    var qop by qopProperty
}

class FormBaseRequestModel(var formBaseRequest: FormBaseRequest) : ViewModel() {
    val request = bind{formBaseRequest.requestProperty}
    val localAddress = bind{formBaseRequest.localAddressProperty}
    val localPort = bind{formBaseRequest.localPortProperty}
    val serverAddress = bind{formBaseRequest.serverAddressProperty}
    val serverPort = bind{formBaseRequest.serverPortProperty}
    val user = bind{formBaseRequest.userProperty}
    val password = bind{formBaseRequest.passwordProperty}
    val callId = bind{formBaseRequest.callIdProperty}
    val seqNumber = bind{formBaseRequest.seqNumberProperty}
    val requestTxtString = bind{formBaseRequest.requestTxtSpringProperty}
    val authorizationEnabled = bind{formBaseRequest.authorizationEnabledProperty}
    val typeAuthorization = bind{formBaseRequest.typeAuthorizationProperty}
    val realmName = bind{formBaseRequest.realmNameProperty}
    val algorithm = bind{formBaseRequest.algorithmProperty}
    val nonce = bind{formBaseRequest.nonceProperty}
    val qop = bind{formBaseRequest.qopProperty}
}

