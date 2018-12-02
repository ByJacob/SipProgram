package pl.edu.pwr.weka.sipprogram.gui.controller.header

import pl.edu.pwr.weka.sipprogram.gui.controller.header.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderAllowModel
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import javax.sip.header.Header

class HeaderAllowController: BaseHeaderController() {
    override val model = HeaderAllowModel()

    override fun toSipHeader(): Header {
        return SipProtocol.headerFactory.createAllowHeader(model.allowList
                .joinToString {it.sipName.toUpperCase()})
    }

}
