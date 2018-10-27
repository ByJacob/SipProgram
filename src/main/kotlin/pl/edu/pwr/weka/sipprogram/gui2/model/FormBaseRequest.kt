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

    val requestProperty = SimpleObjectProperty<RequestEnum>()
    var request by requestProperty

    val localAddressProperty = SimpleStringProperty()
    var localAddress by localAddressProperty

    val localPortProperty = SimpleIntegerProperty()
    var localPort by localPortProperty

    val serverAddressProperty = SimpleStringProperty()
    var serverAddress by serverAddressProperty

    val serverPortProperty = SimpleIntegerProperty()
    var serverPort by serverPortProperty

    val userProperty = SimpleStringProperty()
    var user by userProperty

    val passwordProperty = SimpleStringProperty()
    var password by passwordProperty

    val callIdProperty = SimpleStringProperty()
    var callId by callIdProperty

    val seqNumberProperty = SimpleIntegerProperty()
    var seqNumber by seqNumberProperty

    val requestTxtSpringProperty = SimpleStringProperty()
    var requestTxtSpring by requestTxtSpringProperty
    //auth
    val authorizationEnabledProperty = SimpleBooleanProperty()
    var authorizationEnabled by authorizationEnabledProperty

    val typeAuthorizationProperty = SimpleObjectProperty<AuthMethodEnum>()
    var typeAuthorization by typeAuthorizationProperty

    val realmNameProperty = SimpleStringProperty()
    var realmName by realmNameProperty

    val algorithmProperty = SimpleObjectProperty<AlgorithmEnum>()
    var algorithm by algorithmProperty

    val nonceProperty = SimpleStringProperty()
    var nonce by nonceProperty

    val qopProperty = SimpleObjectProperty<QualityOfProtectionEnum>()
    var qop by qopProperty
}

class FormBaseRequestModel : ItemViewModel<FormBaseRequest>() {
    val request = bind(FormBaseRequest::requestProperty)
    val localAddress = bind(FormBaseRequest::localAddressProperty)
    val localPort = bind(FormBaseRequest::localPortProperty)
    val serverAddress = bind(FormBaseRequest::serverAddressProperty)
    val serverPort = bind(FormBaseRequest::serverPortProperty)
    val user = bind(FormBaseRequest::userProperty)
    val password = bind(FormBaseRequest::passwordProperty)
    val callId = bind(FormBaseRequest::callIdProperty)
    val seqNumber = bind(FormBaseRequest::seqNumberProperty)
    val requestTxtString = bind(FormBaseRequest::requestTxtSpringProperty)
    val authorizationEnabled = bind(FormBaseRequest::authorizationEnabledProperty)
    val typeAuthorization = bind(FormBaseRequest::typeAuthorizationProperty)
    val realmName = bind(FormBaseRequest::realmNameProperty)
    val algorithm = bind(FormBaseRequest::algorithmProperty)
    val nonce = bind(FormBaseRequest::nonceProperty)
    val qop = bind(FormBaseRequest::qopProperty)
}

