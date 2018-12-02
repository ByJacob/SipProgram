package pl.edu.pwr.weka.sipprogram.gui.controller.header

import pl.edu.pwr.weka.sipprogram.gui.controller.header.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderMaxForwardsModel
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import javax.sip.header.Header

class HeaderMaxForwardsController: BaseHeaderController() {
    override val model = HeaderMaxForwardsModel()

    override fun toSipHeader(): Header {
        return SipProtocol.headerFactory.createMaxForwardsHeader(model.maxForwards.value.toInt())
    }

}
