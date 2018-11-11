package pl.edu.pwr.weka.sipprogram.gui.controller.header

import gov.nist.javax.sip.message.SIPRequest
import pl.edu.pwr.weka.sipprogram.gui.controller.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderRequestLineModel
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import javax.sip.header.Header

class HeaderRequestLineController : BaseHeaderController() {

    override fun toSipHeader(): Header {
        throw NotImplementedError("Method don't use. Try use toSipRequest")
    }

    fun toSipRequest(): SIPRequest {
        val model = model as HeaderRequestLineModel
        val createURI = SipProtocol.addressFactory
                .createURI("sip:" + model.requestHost.value + ":" + model.requestPort.value.toString())
        val createRequest = SipProtocol.messageFactory.createRequest("") as SIPRequest
        createRequest.requestURI = createURI
        createRequest.method = model.method.value.sipName
        return createRequest
    }

}
