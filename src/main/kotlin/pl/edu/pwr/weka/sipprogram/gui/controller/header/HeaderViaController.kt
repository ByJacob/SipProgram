package pl.edu.pwr.weka.sipprogram.gui.controller.header

import pl.edu.pwr.weka.sipprogram.gui.controller.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderViaModel
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import javax.sip.header.Header

class HeaderViaController: BaseHeaderController() {

    override fun toSipHeader(): Header {
        val model = model as HeaderViaModel
        return SipProtocol.headerFactory.createViaHeader(model.address.value,
                model.port.value.toInt(), model.protocol.value.sipName, model.branch.value)
    }

}
