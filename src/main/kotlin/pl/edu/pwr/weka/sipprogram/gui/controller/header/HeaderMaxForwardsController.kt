package pl.edu.pwr.weka.sipprogram.gui.controller.header

import pl.edu.pwr.weka.sipprogram.gui.controller.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderMaxForwardsModel
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import javax.sip.header.Header

class HeaderMaxForwardsController: BaseHeaderController() {
    override fun toSipHeader(): Header {
        val model = model as HeaderMaxForwardsModel
        return SipProtocol.headerFactory.createMaxForwardsHeader(model.maxForwards.value.toInt())
    }

}