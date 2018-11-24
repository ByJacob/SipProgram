package pl.edu.pwr.weka.sipprogram.gui.controller.header

import pl.edu.pwr.weka.sipprogram.gui.controller.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderAllowModel
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderSupportedModel
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import javax.sip.header.Header

class HeaderSupportedController: BaseHeaderController() {
    override fun toSipHeader(): Header {
        val model = model as HeaderSupportedModel
        return SipProtocol.headerFactory.createSupportedHeader(model.supportedList
                .joinToString {it.sipName})
    }

}
