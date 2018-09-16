package pl.edu.pwr.weka.sipprogram.sip.request

import org.slf4j.LoggerFactory
import pl.edu.pwr.weka.sipprogram.sip.request.base.RequestEnum
import pl.edu.pwr.weka.sipprogram.util.IdGenerator
import java.net.InetAddress
import java.util.*
import javax.sip.ListeningPoint
import javax.sip.SipFactory
import javax.sip.SipProvider
import javax.sip.SipStack
import javax.sip.address.URI
import javax.sip.header.*
import javax.sip.message.Request
import java.util.UUID



/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 04.09.2018 22:30
 */


class Request {

    val log = LoggerFactory.getLogger(this::class.java)
    var method: RequestEnum = RequestEnum.REGISTER
    var serwerAddress = "192.168.1.108"
        set(value) {
            requestUrlAddress = "sip:$value:$serwerPort"
            toHeaderAddress = "sip:$userLogin@$value:$serwerPort"
        }
    var serwerPort: Int = 5160
        set(value) {
            requestUrlAddress = "sip:$serwerAddress:$value"
            toHeaderAddress = "sip:$userLogin@$serwerAddress:$value"
        }
    var userLogin = "111"
        set(value) {
            toHeaderAddress = "sip:$value@$serwerAddress:$localPort"
            contactAddress = "sip:$value@$localIpAddress:$localPort"
        }
    var userPassword = "111"
    var targetUser = "222"
        set(value) {
            fromHeaderAddress = "sip:$value@$localIpAddress:$localPort"
        }
    var localIpAddress: String = InetAddress.getLocalHost().hostAddress
        set(value) {
            fromHeaderAddress = "sip:$targetUser@$value:$localPort"
            contactAddress = "sip:$userLogin@$value:$localPort"
        }
    var localPort: Int = 8080
        set(value) {
            fromHeaderAddress = "sip:$targetUser@$localIpAddress:$value"
            contactAddress = "sip:$userLogin@$localIpAddress:$value"
        }
    var sipFactory = SipFactory.getInstance()
    var properties = Properties()
    var requestUrlAddress = "sip:$serwerAddress:$serwerPort"
    var toHeaderAddress = "sip:$userLogin@$serwerAddress:$serwerPort"
    var toHeaderTag: String = ""
    var fromHeaderAddress = "sip:$targetUser@$localIpAddress:$localPort"
    var fromHeaderTag: String = Random().nextInt().toString()
    var callId: String
    var protocol: String = "udp"
    var sequenceNumber = IdGenerator.generateUniqueId()
    var branchName = "z9hG4bK" + UUID.randomUUID().toString().replace("-", "")
    var maxForwards = 70
    var contactAddress = "sip:$userLogin@$localIpAddress:$localPort"
    var expires = 300

    val sipProvider: SipProvider by lazy {
        getSipStack().createSipProvider(getListeningPoint())
    }

    init {
        properties.setProperty("javax.sip.STACK_NAME", "stack")
        callId = sipProvider.newCallId.callId
    }

    fun prepareRequest(): Request {
        val createRequest = sipFactory.createMessageFactory().createRequest(
                getRequestURI(),
                method.name,
                getCallIdHeader(),
                getCSeqHeader(),
                getFromHeader(),
                getToHeader(),
                getViaHeader(),
                getMaxForwardersHeader()
        )
        createRequest.addHeader(getContactHeader())
        return createRequest
    }

    fun sendRequest() {
        sipProvider.sendRequest(prepareRequest())
        log.debug("Send:" + prepareRequest().toString())
    }

    private fun getRequestURI(): URI {
        return sipFactory.createAddressFactory().createURI(requestUrlAddress)
    }

    private fun getToHeader(): ToHeader {
        return if (toHeaderTag.isEmpty())
            sipFactory.createHeaderFactory().createToHeader(
                    sipFactory.createAddressFactory().createAddress(toHeaderAddress), null)
        else
            sipFactory.createHeaderFactory().createToHeader(
                    sipFactory.createAddressFactory().createAddress(toHeaderAddress), toHeaderTag)
    }

    private fun getFromHeader(): FromHeader {
        return if (fromHeaderTag.isEmpty())
            sipFactory.createHeaderFactory().createFromHeader(
                    sipFactory.createAddressFactory().createAddress(fromHeaderAddress), null)
        else
            sipFactory.createHeaderFactory().createFromHeader(
                    sipFactory.createAddressFactory().createAddress(fromHeaderAddress), fromHeaderTag)
    }

    private fun getSipStack(): SipStack {
        return sipFactory.createSipStack(properties)
    }

    private fun getListeningPoint(): ListeningPoint {
        return getSipStack().createListeningPoint(localIpAddress, localPort, protocol)
    }

    private fun getCallIdHeader(): CallIdHeader {
        return sipFactory.createHeaderFactory().createCallIdHeader(callId)
    }

    private fun getCSeqHeader(): CSeqHeader {
        sequenceNumber += 1
        return sipFactory.createHeaderFactory().createCSeqHeader(sequenceNumber, method.name)
    }

    private fun getViaHeader(): ArrayList<ViaHeader> {
        return if (branchName.isEmpty())
            arrayListOf(sipFactory.createHeaderFactory()
                    .createViaHeader(localIpAddress, localPort, "udp", null))
        else
            arrayListOf(sipFactory.createHeaderFactory()
                    .createViaHeader(localIpAddress, localPort, "udp", branchName))
    }

    private fun getMaxForwardersHeader(): MaxForwardsHeader {
        return sipFactory.createHeaderFactory().createMaxForwardsHeader(maxForwards)
    }

    private fun getContactHeader(): ContactHeader {
        val createContactHeader = sipFactory.createHeaderFactory()
                .createContactHeader(sipFactory.createAddressFactory().createAddress(contactAddress))
        createContactHeader.expires = expires
        return createContactHeader
    }

}