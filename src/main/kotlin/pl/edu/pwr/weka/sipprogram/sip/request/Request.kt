package pl.edu.pwr.weka.sipprogram.sip.request

import org.slf4j.LoggerFactory
import pl.edu.pwr.weka.sipprogram.sip.request.base.RequestEnum
import pl.edu.pwr.weka.sipprogram.util.IdGenerator
import java.net.InetAddress
import java.util.*
import java.util.UUID
import javax.sip.*
import javax.sip.address.URI
import javax.sip.header.*
import javax.sip.message.Request


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
            field = value
            refreshVariable()
        }
    var serwerPort: Int = 5160
        set(value) {
            field = value
            refreshVariable()
        }
    var userLogin = "111"
        set(value) {
            field = value
            refreshVariable()
        }
    var userPassword = "111"
    var targetUser = "111"
        set(value) {
            field = value
            refreshVariable()
        }
    var localIpAddress: String = InetAddress.getLocalHost().hostAddress
        set(value) {
            field = value
            refreshVariable()
        }
    var localPort: Int = 8080
        set(value) {
            field = value
            refreshVariable()
        }
    var sipFactory = SipFactory.getInstance()
    var properties = Properties()
    var requestUrlAddress = ""
    var toHeaderAddress = ""
    var toHeaderTag: String = ""
    var fromHeaderAddress = ""
    var fromHeaderTag: String = Random().nextInt().toString()
    var callId: String
    var protocol: String = "udp"
    var sequenceNumber = IdGenerator.generateUniqueId()
    var branchName = "z9hG4bK" + UUID.randomUUID().toString().replace("-", "")
    var maxForwards = 70
    var contactAddress = "sip:$userLogin@$localIpAddress:$localPort"
    var expires = 300

    init {
        properties.setProperty("javax.sip.STACK_NAME", "stack")
        callId = getSipProvider().newCallId.callId
        refreshVariable()
    }

    private fun refreshVariable() {
        contactAddress = "sip:$userLogin@$localIpAddress:$localPort"
        requestUrlAddress = "sip:$serwerAddress:$serwerPort"
        toHeaderAddress = "sip:$userLogin@$serwerAddress"
        fromHeaderAddress = "sip:$targetUser@$localIpAddress"
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

    fun sendRequest(listener: SipListener) {
        getSipProvider().addSipListener(listener)
        getSipProvider().sendRequest(prepareRequest())
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

    private fun getSipProvider(): SipProvider {
        return if (!getSipStack().sipProviders.hasNext())
            getSipStack().createSipProvider(getListeningPoint())
        else
            getSipStack().sipProviders.next() as SipProvider
    }

    private fun getListeningPoint(): ListeningPoint {
        return getSipStack().createListeningPoint(localIpAddress, localPort, protocol)
    }

    private fun getCallIdHeader(): CallIdHeader {
        return sipFactory.createHeaderFactory().createCallIdHeader(callId)
    }

    private fun getCSeqHeader(): CSeqHeader {
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