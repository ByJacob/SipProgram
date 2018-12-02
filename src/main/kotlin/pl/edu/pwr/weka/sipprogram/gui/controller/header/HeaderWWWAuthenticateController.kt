package pl.edu.pwr.weka.sipprogram.gui.controller.header

import pl.edu.pwr.weka.sipprogram.gui.controller.header.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderWWWAuthenticateModel
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import javax.sip.header.Header

class HeaderWWWAuthenticateController: BaseHeaderController() {
    override var model = HeaderWWWAuthenticateModel()

    override fun toSipHeader(): Header {
        val createWWWAuthenticateHeader = SipProtocol.headerFactory
                .createWWWAuthenticateHeader(model.scheme.value.sipName)
        createWWWAuthenticateHeader.algorithm = model.algorithm.value.sipName
        createWWWAuthenticateHeader.realm = model.realm.value
        createWWWAuthenticateHeader.nonce = model.nonce.value
        return createWWWAuthenticateHeader
    }

}
