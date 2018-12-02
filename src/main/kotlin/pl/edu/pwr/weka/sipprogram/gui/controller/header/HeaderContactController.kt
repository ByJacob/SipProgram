package pl.edu.pwr.weka.sipprogram.gui.controller.header

import pl.edu.pwr.weka.sipprogram.gui.controller.header.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderContactModel
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import javax.sip.header.Header

class HeaderContactController: BaseHeaderController() {
    override val model = HeaderContactModel()

    override fun toSipHeader(): Header {
        val address = "sip:"+model.user.value+"@"+model.address.value+":"+model.port.value.toString()
        val createContactHeader = SipProtocol.headerFactory.createContactHeader(
                SipProtocol.addressFactory.createAddress(address)
        )
        createContactHeader.expires = model.expires.value.toInt()
        return createContactHeader
    }

}
