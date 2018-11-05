package pl.edu.pwr.weka.sipprogram.gui2.model

import javafx.beans.property.*
import pl.edu.pwr.weka.sipprogram.gui2.view.ProcessConnectionView
import pl.edu.pwr.weka.sipprogram.gui2.view.fragment.FormRequestFragment
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

class FormRequest {

    val isSendingRequestProperty = SimpleBooleanProperty(true)
    var isSendingRequest by isSendingRequestProperty

    val requestProperty = SimpleObjectProperty<RequestEnum>(RequestEnum.REGISTER)
    var request by requestProperty

    val localAddressProperty = SimpleStringProperty("")
    var localAddress by localAddressProperty

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

    val seqNumberProperty = SimpleLongProperty(0)
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

    val fromTagProperty = SimpleStringProperty("")
    var fromTag by fromTagProperty

    val branchProperty = SimpleStringProperty("")
    var branch by branchProperty


}

class FormRequestModel(var formRequest: FormRequest) : ViewModel() {
    val request = bind(true) { formRequest.requestProperty }
    val localAddress = bind(true) { formRequest.localAddressProperty }
    val serverAddress = bind(true) { formRequest.serverAddressProperty }
    val serverPort = bind(true) { formRequest.serverPortProperty }
    val user = bind(true) { formRequest.userProperty }
    val password = bind(true) { formRequest.passwordProperty }
    val callId = bind(true) { formRequest.callIdProperty }
    val seqNumber = bind(true) { formRequest.seqNumberProperty }
    val requestTxtString = bind(true) { formRequest.requestTxtSpringProperty }
    val authorizationEnabled = bind(true) { formRequest.authorizationEnabledProperty }
    val typeAuthorization = bind(true) { formRequest.typeAuthorizationProperty }
    val realmName = bind(true) { formRequest.realmNameProperty }
    val algorithm = bind(true) { formRequest.algorithmProperty }
    val nonce = bind(true) { formRequest.nonceProperty }
    val qop = bind(true) { formRequest.qopProperty }
    val isSendingRequest = bind(true) { formRequest.isSendingRequestProperty }
    val fromTag = bind(true) { formRequest.fromTagProperty }
    val branch = bind(true) { formRequest.branchProperty }

    val sendUpdateRequestValues = listOf<Property<*>>(
            request, localAddress, serverAddress, serverPort, user, password,
            callId, seqNumber, authorizationEnabled, typeAuthorization, realmName, algorithm,
            nonce, qop
    )

    init {
        request.addListener { _, _, _ ->
            fire(ProcessConnectionView.RefreshListFormRequestEvent())
        }
        authorizationEnabled.addListener { _, _, newValue ->
            fire(FormRequestFragment.EnableAuthorizationEvent(newValue))
        }
        sendUpdateRequestValues.forEach {
            it.addListener { _, _, _ ->
                fire(FormRequestFragment.UpdateRequestTextAreaEvent())
            }
        }
    }

    override fun toString(): String {
        return request.value.toString()
    }
}

