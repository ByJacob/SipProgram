package pl.edu.pwr.weka.sipprogram.gui.controller.header

import pl.edu.pwr.weka.sipprogram.gui.controller.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderCSeqModel
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import tornadofx.*
import javax.sip.header.Header

class HeaderCSeqController: BaseHeaderController() {
    override val model = HeaderCSeqModel()

    override fun toSipHeader(): Header {
        return SipProtocol.headerFactory.createCSeqHeader(model.number.value.toLong(), model.method.value.sipName)
    }

}
