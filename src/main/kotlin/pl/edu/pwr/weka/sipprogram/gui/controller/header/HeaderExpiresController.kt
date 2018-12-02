package pl.edu.pwr.weka.sipprogram.gui.controller.header

import pl.edu.pwr.weka.sipprogram.gui.controller.header.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderExpiresModel
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import javax.sip.header.Header

class HeaderExpiresController: BaseHeaderController() {
    override val model = HeaderExpiresModel()

    override fun toSipHeader(): Header {
        return SipProtocol.headerFactory.createExpiresHeader(model.expires.value.toInt())
    }

}
