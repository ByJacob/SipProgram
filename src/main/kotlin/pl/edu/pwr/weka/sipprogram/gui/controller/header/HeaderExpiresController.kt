package pl.edu.pwr.weka.sipprogram.gui.controller.header

import pl.edu.pwr.weka.sipprogram.gui.controller.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderExpiresModel
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import javax.sip.header.Header

class HeaderExpiresController: BaseHeaderController() {
    override fun toSipHeader(): Header {
        val model = model as HeaderExpiresModel
        return SipProtocol.headerFactory.createExpiresHeader(model.expires.value.toInt())
    }

}
