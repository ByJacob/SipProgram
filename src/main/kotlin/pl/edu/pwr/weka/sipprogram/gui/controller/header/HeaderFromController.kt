package pl.edu.pwr.weka.sipprogram.gui.controller.header

import pl.edu.pwr.weka.sipprogram.gui.controller.header.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderFromModel
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import javax.sip.header.Header

class HeaderFromController: BaseHeaderController() {
    override val model = HeaderFromModel()

    fun initValues(){
        model.address.value = SipProtocol.ip
        model.port.value = SipProtocol.port

    }

    override fun toSipHeader(): Header {
        return SipProtocol.headerFactory.createFromHeader(
                SipProtocol.addressFactory.createAddress(
                        "sip:${model.user.value}@${model.address.value}:${model.port.value}"
                ),
                model.tag.value
        )
    }

}
