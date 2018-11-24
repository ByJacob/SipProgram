package pl.edu.pwr.weka.sipprogram.gui.controller.header

import pl.edu.pwr.weka.sipprogram.gui.controller.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderToModel
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import javax.sip.header.Header

class HeaderToController : BaseHeaderController() {

    override val model = HeaderToModel()
    override fun toSipHeader(): Header {
        return SipProtocol.headerFactory.createToHeader(
                SipProtocol.addressFactory.createAddress(
                        "sip:${model.user.value}@${model.address.value}:${model.port.value}"
                ),
                null)
    }

}
