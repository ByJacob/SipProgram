package pl.edu.pwr.weka.sipprogram.gui.controller.header

import org.apache.commons.codec.digest.DigestUtils
import pl.edu.pwr.weka.sipprogram.gui.controller.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderAuthorizationModel
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import javax.sip.header.Header

class HeaderAuthenticationController: BaseHeaderController() {
    override val model = HeaderAuthorizationModel()

    override fun toSipHeader(): Header {
        val authorizationHeader = SipProtocol.headerFactory
                .createAuthorizationHeader(model.scheme.value.sipName)
        authorizationHeader.algorithm = model.algorithm.value.sipName
        authorizationHeader.username = model.username.value
        authorizationHeader.realm = model.realm.value
        authorizationHeader.nonce = model.nonce.value
        authorizationHeader.uri = SipProtocol.addressFactory
                .createURI("sip:${model.authenticationUriHost.value}:${model.authenticationUriPort.value}")
        authorizationHeader.response = generateResponseValue()
        return authorizationHeader
    }

    private fun generateResponseValue(): String {
        print("${model.username.value}:${model.realm.value}:${model.password.value}")
        val authenticationUri = "sip:${model.authenticationUriHost.value}:${model.authenticationUriPort.value}"
        val h1 = DigestUtils.md5Hex("${model.username.value}:${model.realm.value}:${model.password.value}")
        val h2 = DigestUtils.md5Hex("${model.method.value.sipName.toUpperCase()}:$authenticationUri")
        return DigestUtils.md5Hex("$h1:${model.nonce.value}:$h2")
    }

}
