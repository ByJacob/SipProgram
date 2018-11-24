package pl.edu.pwr.weka.sipprogram.gui.controller.header

import pl.edu.pwr.weka.sipprogram.gui.controller.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderUserAgentModel
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import javax.sip.header.Header

class HeaderUserAgentController: BaseHeaderController() {
    override var model = HeaderUserAgentModel()

    override fun toSipHeader(): Header {
        return SipProtocol.headerFactory.createUserAgentHeader(listOf(model.userAgent.value))
    }

}
