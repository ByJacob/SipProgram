package pl.edu.pwr.weka.sipprogram.gui.controller.header

import pl.edu.pwr.weka.sipprogram.gui.controller.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderContactModel
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import tornadofx.*
import javax.sip.header.Header

class HeaderContactController: BaseHeaderController() {
    override val model = HeaderContactModel()

    override fun toSipHeader(): Header {
        val address = "sip:"+model.user.value+"@"+model.address.value+":"+model.port.value.toString()
        return SipProtocol.headerFactory.createContactHeader(
                SipProtocol.addressFactory.createAddress(address)
        )
    }

}
