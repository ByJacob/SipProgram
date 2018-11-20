package pl.edu.pwr.weka.sipprogram.gui.controller.header

import pl.edu.pwr.weka.sipprogram.gui.controller.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderAllowModel
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import javax.sip.header.Header

class HeaderAllowController: BaseHeaderController() {
    override fun toSipHeader(): Header {
        val model = model as HeaderAllowModel
        return SipProtocol.headerFactory.createAllowHeader(model.allowList
                .joinToString {it.sipName.toUpperCase()})
    }

}
