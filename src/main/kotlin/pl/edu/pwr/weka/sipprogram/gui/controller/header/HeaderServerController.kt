package pl.edu.pwr.weka.sipprogram.gui.controller.header

import pl.edu.pwr.weka.sipprogram.gui.controller.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderServerModel
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import javax.sip.header.Header

class HeaderServerController : BaseHeaderController() {
    override val model = HeaderServerModel()

    override fun toSipHeader(): Header {
        return SipProtocol.headerFactory.createServerHeader(listOf(model.server.value))
    }

}