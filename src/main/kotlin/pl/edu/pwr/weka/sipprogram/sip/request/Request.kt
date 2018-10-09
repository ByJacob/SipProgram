package pl.edu.pwr.weka.sipprogram.sip.request

import gov.nist.javax.sip.ResponseEventExt
import gov.nist.javax.sip.Utils
import gov.nist.javax.sip.header.*
import gov.nist.javax.sip.message.SIPRequest
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
    var serverAddress = "192.168.1.108"
    var serverPort: Int = 5160
    var userLogin = "111"
        set(value) {
            field = value; authorization.username = value
        }
    var userPassword = "111"
        set(value) {
            field = value; authorization.password = value
        }
    var targetUser = "111"
    var localIpAddress: String = InetAddress.getLocalHost().hostAddress
    var localPort: Int = 8080
    var sipFactory = SipFactory.getInstance()
    var properties = Properties()
    var requestUrlAddress = ""
    var toHeaderAddress = ""
    var toHeaderTag: String = ""
    var fromHeaderAddress = ""
    var fromHeaderTag: String = Random().nextInt().toString()
    var callId: String
    var protocol: String = "udp"
    var sequenceNumber = IdGenerator.generateUniqueId().toLong()
    var branchName = "z9hG4bK" + UUID.randomUUID().toString().replace("-", "")
    var maxForwards = 70
    var contactAddress = "sip:$userLogin@$localIpAddress:$localPort"
    var expires = 300
    val listHeader = mutableListOf<Header>()
    val authorization = Authorization()

    init {
        properties.setProperty("javax.sip.STACK_NAME", "stack")
        callId = Utils.getInstance().generateCallIdentifier(localIpAddress)
        refreshVariable()
    }

    private fun refreshVariable() {
        when (method) {
            RequestEnum.REGISTER -> targetUser = userLogin
            else -> {
            }
        }
        contactAddress = "sip:$userLogin@$localIpAddress:$localPort"
        requestUrlAddress = "sip:$serverAddress:$serverPort"
        toHeaderAddress = "sip:$userLogin@$serverAddress"
        fromHeaderAddress = "sip:$targetUser@$localIpAddress"
    }

    fun prepareRequest(): Request {
        refreshVariable()
        recreateHeaders()
        val createRequest = sipFactory.createMessageFactory().createRequest("") as SIPRequest
        createRequest.requestURI = getRequestURI()
        createRequest.method = method.name
        listHeader.forEach { createRequest.attachHeader(it as SIPHeader, true, false) }
        return createRequest
    }

    fun sendRequest(listener: SipListener) {
        getSipProvider().addSipListener(listener)
        getSipProvider().sendRequest(prepareRequest())
        log.debug("Send:" + prepareRequest().toString())
    }

    fun processResponse(re: ResponseEventExt) {
        serverAddress = re.remoteIpAddress.toString()
        serverPort = re.remotePort
        re.response.headerNames.forEach {
            when (it.toString()) {
                SIPHeaderNames.VIA -> {
                    for (via in re.response.getHeader(it.toString()) as ViaList) {
                        localIpAddress = via.sentBy.host.address
                        localPort = via.sentBy.port
                    }
                }
                SIPHeaderNames.CALL_ID -> {
                    val header = re.response.getHeader(it.toString()) as CallID
                    callId = header.callId
                }
                SIPHeaderNames.CSEQ -> {
                    val header = re.response.getHeader(it.toString()) as CSeq
                    sequenceNumber = header.seqNumber
                }
            }
        }
    }

    private fun recreateHeaders() {
        listHeader.clear()
        listHeader.add(getCallIdHeader())
        listHeader.add(getCSeqHeader())
        listHeader.add(getFromHeader())
        listHeader.add(getToHeader())
        listHeader.add(getViaHeader())
        listHeader.add(getMaxForwardersHeader())
        listHeader.add(getContactHeader())
        if (authorization.enabled)
            listHeader.add(authorization.takeHeader(sipFactory))
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
        val sipProviders = getSipStack().sipProviders as Iterator<SipProvider>
        while (sipProviders.hasNext()) {
            val nextSipProviders = sipProviders.next()
            if (nextSipProviders.listeningPoints[0] == getListeningPoint()) {
                return nextSipProviders
            }
        }
        return getSipStack().createSipProvider(getListeningPoint())
    }

    private fun getListeningPoint(): ListeningPoint {
        val listeningPoints = getSipStack().listeningPoints as Iterator<ListeningPoint>
        while (listeningPoints.hasNext()) {
            val nextListening = listeningPoints.next()
            if (nextListening.ipAddress == localIpAddress && nextListening.port == localPort)
                return nextListening
        }
        return getSipStack().createListeningPoint(localIpAddress, localPort, protocol)
    }

    private fun getCallIdHeader(): CallIdHeader {
        return sipFactory.createHeaderFactory().createCallIdHeader(callId)
    }

    private fun getCSeqHeader(): CSeqHeader {
        return sipFactory.createHeaderFactory().createCSeqHeader(sequenceNumber, method.name)
    }

    private fun getViaHeader(): ViaList {
        val viaList = ViaList()
        if (branchName.isEmpty())
            viaList.add(sipFactory.createHeaderFactory()
                    .createViaHeader(localIpAddress, localPort, "udp", null) as Via)
        else
            viaList.add(sipFactory.createHeaderFactory()
                    .createViaHeader(localIpAddress, localPort, "udp", branchName) as Via)
        return viaList
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