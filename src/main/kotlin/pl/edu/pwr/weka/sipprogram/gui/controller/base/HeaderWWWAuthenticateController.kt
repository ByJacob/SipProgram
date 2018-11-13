package pl.edu.pwr.weka.sipprogram.gui.controller.base

import pl.edu.pwr.weka.sipprogram.gui.controller.base.BaseHeaderController
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderAllowModel
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderWWWAuthenticate
import pl.edu.pwr.weka.sipprogram.gui.model.header.HeaderWWWAuthenticateModel
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import javax.sip.header.Header

class HeaderWWWAuthenticateController: BaseHeaderController() {
    override fun toSipHeader(): Header {
        val model = model as HeaderWWWAuthenticateModel
        val createWWWAuthenticateHeader = SipProtocol.headerFactory
                .createWWWAuthenticateHeader(model.scheme.value.sipName)
        createWWWAuthenticateHeader.algorithm = model.algorithm.value.sipName
        createWWWAuthenticateHeader.realm = model.realm.value
        createWWWAuthenticateHeader.nonce = model.nonce.value
        return createWWWAuthenticateHeader
    }

}
