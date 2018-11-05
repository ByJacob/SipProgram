package pl.edu.pwr.weka.sipprogram.sip.request.method

import pl.edu.pwr.weka.sipprogram.gui2.model.FormRequest
import pl.edu.pwr.weka.sipprogram.sip.request.Request
import pl.edu.pwr.weka.sipprogram.sip.request.base.Headers
import pl.edu.pwr.weka.sipprogram.sip.request.base.RequestEnum

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 05.11.2018 22:34
 */
class Reqister : Request() {

    init {
        method = RequestEnum.REGISTER
    }

    override fun recreateHeaders() {
        super.recreateHeaders()
        listHeader.add(Headers.createViaHeaders(localIpAddress, localPort, branchName))
        listHeader.add(Headers.createMaxForwardersHeader(maxForwards))
        listHeader.add(Headers.createFromHeader(userLogin, localIpAddress, fromHeaderTag))
        listHeader.add(Headers.createToHeader(userLogin, localIpAddress))
        listHeader.add(Headers.createCallIdHeader(callId))
        listHeader.add(Headers.createCSeqHeader(sequenceNumber, RequestEnum.REGISTER))
        listHeader.add(Headers.createUserAgentHeader())
        listHeader.add(Headers.createContactHeader(expires, userLogin, localIpAddress, localPort))
        listHeader.add(Headers.createAllowHeader())
    }

    override fun mapToFormRequest(): FormRequest {
        return super.mapToFormRequest()
    }
}