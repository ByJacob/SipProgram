package pl.edu.pwr.weka.sipprogram.gui.controller.header

import gov.nist.javax.sip.message.SIPRequest
import javafx.collections.FXCollections
import pl.edu.pwr.weka.sipprogram.gui.controller.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderRequestLineModel
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.RequestEnum
import javax.sip.header.Header

class HeaderRequestLineController : BaseHeaderController() {

    override val model = HeaderRequestLineModel()
    val requestMethod = FXCollections.observableArrayList(listOf(RequestEnum.REGISTER,
            RequestEnum.INVITE, RequestEnum.INVITE))

    override fun toSipHeader(): Header {
        throw NotImplementedError("Method don't use. Try use toSipRequest")
    }

    fun toSipRequest(): SIPRequest {
        val createURI = SipProtocol.addressFactory
                .createURI("sip:" + model.requestHost.value + ":" + model.requestPort.value.toString())
        val createRequest = SipProtocol.messageFactory.createRequest("") as SIPRequest
        createRequest.requestURI = createURI
        createRequest.method = model.method.value.sipName
        return createRequest
    }

}
