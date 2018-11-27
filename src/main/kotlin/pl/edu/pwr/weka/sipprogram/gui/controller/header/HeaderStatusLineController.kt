package pl.edu.pwr.weka.sipprogram.gui.controller.header

import gov.nist.javax.sip.message.SIPResponse
import pl.edu.pwr.weka.sipprogram.gui.controller.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderStatusLineModel
import javax.sip.header.Header

class HeaderStatusLineController : BaseHeaderController() {

    override val model = HeaderStatusLineModel()

    init {
        model.statusCode.addListener { _, _, newValue ->
            newValue?.let {
                model.headerStatusLineRow.message = SIPResponse.getReasonPhrase(newValue.toInt())
            }
        }
    }

    override fun toSipHeader(): Header {
        throw NotImplementedError("Method don't use. Try use toSipResponse")
    }

    /*fun toSipResponse(): SIPRequest {
        val createURI = SipProtocol.addressFactory
                .createURI("sip:" + model.requestHost.value + ":" + model.requestPort.value.toString())
        val createRequest = SipProtocol.messageFactory.createRequest("") as SIPRequest
        createRequest.requestURI = createURI
        createRequest.method = model.method.value.sipName
        return createRequest
    }*/

}
