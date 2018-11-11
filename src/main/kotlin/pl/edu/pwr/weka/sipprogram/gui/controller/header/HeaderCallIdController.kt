package pl.edu.pwr.weka.sipprogram.gui.controller.header

import pl.edu.pwr.weka.sipprogram.gui.controller.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderCallIdModel
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import javax.sip.header.Header

class HeaderCallIdController : BaseHeaderController() {
    override fun toSipHeader(): Header {
        val model = model as HeaderCallIdModel
        return SipProtocol.headerFactory.createCallIdHeader(model.callId.value)
    }

}
