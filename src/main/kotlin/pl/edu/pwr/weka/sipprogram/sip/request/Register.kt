package pl.edu.pwr.weka.sipprogram.sip.request

import pl.edu.pwr.weka.sipprogram.sip.request.base.BaseRequest
import pl.edu.pwr.weka.sipprogram.sip.request.base.RequestEnum
import java.util.*
import javax.sip.ListeningPoint
import javax.sip.SipFactory
import javax.sip.SipProvider
import javax.sip.SipStack
import javax.sip.address.URI
import javax.sip.header.CSeqHeader
import javax.sip.header.CallIdHeader
import javax.sip.header.FromHeader
import javax.sip.header.ToHeader

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 04.09.2018 22:30
 */


class Register(val sipFactory: SipFactory,
               val properties: Properties,
               val serwerURL: String,
               val userLogin: String,
               val userPassword: String): BaseRequest() {

    val METHOD = RequestEnum.REQUEST.name
    var requestUrlAddress = "sip:$serwerURL"
    var toHeaderAddress = "sip:$userLogin@$serwerURL"
    var toHeaderTag: String? = null
    var fromHeaderAddress = "sip:$userLogin@$serwerURL"
    var fromHeaderTag: String? = Random().nextInt().toString()
    var localIpAddress: String = "192.168.1.104"
    var localPort: Int = 8080
    var protocol: String = "udp"
    var sequenceNumber = 0L

    private fun getRequestURI(): URI {
        return sipFactory.createAddressFactory().createURI(requestUrlAddress)
    }

    private fun getToHeader(): ToHeader {
        return sipFactory.createHeaderFactory().createToHeader(
                sipFactory.createAddressFactory().createAddress(toHeaderAddress), toHeaderTag)
    }

    private fun getFromHeader(): FromHeader {
        return sipFactory.createHeaderFactory().createFromHeader(
                sipFactory.createAddressFactory().createAddress(fromHeaderAddress), fromHeaderTag)
    }

    private fun getSipStack(): SipStack {
        return sipFactory.createSipStack(properties)
    }

    private fun getListeningPoint(): ListeningPoint {
        return getSipStack().createListeningPoint(localIpAddress, localPort, protocol)
    }

    private fun getSipProvider(): SipProvider {
        return getSipStack().createSipProvider(getListeningPoint())
    }

    private fun getCallIdHeader(): CallIdHeader {
        return getSipProvider().newCallId
    }

    private fun getCSeqHeader(): CSeqHeader {
        sequenceNumber += 1
        return sipFactory.createHeaderFactory().createCSeqHeader(sequenceNumber, METHOD)
    }

}