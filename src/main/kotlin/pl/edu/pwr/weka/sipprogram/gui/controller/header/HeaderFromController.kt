package pl.edu.pwr.weka.sipprogram.gui.controller.header

import pl.edu.pwr.weka.sipprogram.gui.controller.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderFromModel
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import javax.sip.header.Header

class HeaderFromController: BaseHeaderController() {

    fun initValues(){
        val model = model as HeaderFromModel
        model.address.value = SipProtocol.ip
        model.port.value = SipProtocol.port

    }

    override fun toSipHeader(): Header {
        val model = model as HeaderFromModel
        return SipProtocol.headerFactory.createFromHeader(
                SipProtocol.addressFactory.createAddress(
                        "sip:"+model.address.value+":"+model.port.value.toString()
                ),
                model.tag.value
        )
    }

}
