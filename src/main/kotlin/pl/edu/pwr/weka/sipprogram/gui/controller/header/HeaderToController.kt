package pl.edu.pwr.weka.sipprogram.gui.controller.header

import pl.edu.pwr.weka.sipprogram.gui.controller.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderToModel
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import javax.sip.header.Header

class HeaderToController : BaseHeaderController() {
    override fun toSipHeader(): Header {
        val model = model as HeaderToModel
        return SipProtocol.headerFactory.createToHeader(
                SipProtocol.addressFactory.createAddress(
                        "sip:" + model.address.value + ":" + model.port.value.toString()
                ),
                null)
    }

}
