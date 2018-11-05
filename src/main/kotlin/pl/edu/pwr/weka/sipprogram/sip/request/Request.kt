package pl.edu.pwr.weka.sipprogram.sip.request

import gov.nist.javax.sip.ResponseEventExt
import gov.nist.javax.sip.Utils
import gov.nist.javax.sip.header.*
import gov.nist.javax.sip.message.SIPRequest
import org.slf4j.LoggerFactory
import pl.edu.pwr.weka.sipprogram.gui2.model.FormRequest
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import pl.edu.pwr.weka.sipprogram.sip.auth.enums.AuthMethodEnum
import pl.edu.pwr.weka.sipprogram.sip.request.base.RequestEnum
import pl.edu.pwr.weka.sipprogram.util.IdGenerator
import java.util.*
import java.util.UUID
import javax.sip.address.URI
import javax.sip.header.Header
import javax.sip.message.Request


/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 04.09.2018 22:30
 */


open class Request {

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
    var localIpAddress = SipProtocol.ip
    var localPort: Int = SipProtocol.port
    var requestUrlAddress = ""
    var toHeaderAddress = ""
    var toHeaderTag: String = ""
    var fromHeaderAddress = ""
    var fromHeaderTag: String = Random().nextInt().toString()
    var callId: String
    var sequenceNumber = IdGenerator.generateUniqueId().toLong()
    var branchName = "z9hG4bK" + UUID.randomUUID().toString().replace("-", "")
    var maxForwards = 70
    var contactAddress = "sip:$userLogin@$localIpAddress:$localPort"
    var expires = 300
    val listHeader = mutableListOf<Header>()
    val authorization = Authorization()

    init {
        callId = Utils.getInstance().generateCallIdentifier(localIpAddress)
    }

    fun prepareRequest(): Request {
        recreateHeaders()
        val createRequest = SipProtocol.messageFactory.createRequest("") as SIPRequest
        createRequest.requestURI = getRequestURI()
        createRequest.method = method.name
        listHeader.forEach { createRequest.attachHeader(it as SIPHeader, true, false) }
        return createRequest
    }

    open fun recreateHeaders() {
        listHeader.clear()
    }

    open fun mapToFormRequest(): FormRequest {
        val formRequest = FormRequest()
        formRequest.request = this.method
        formRequest.serverAddress = this.serverAddress
        formRequest.serverPort = this.serverPort
        formRequest.user = this.userLogin
        formRequest.password = this.userPassword
        //formRequest.targetUser = ""
        formRequest.localAddress = this.localIpAddress
        formRequest.callId = this.callId
        formRequest.seqNumber = this.sequenceNumber
        formRequest.fromTag = this.fromHeaderTag
        formRequest.branch = this.branchName

        formRequest.authorizationEnabled = this.authorization.enabled
        formRequest.typeAuthorization = this.authorization.type
        formRequest.realmName = this.authorization.realm
        formRequest.algorithm = this.authorization.algorithm
        formRequest.nonce = this.authorization.nonce
        formRequest.qop = this.authorization.qop
        return formRequest
    }

    fun sendRequest() {
        SipProtocol.sipProvider.sendRequest(prepareRequest())
        log.debug("Send:" + prepareRequest().toString())
    }

    fun processResponse(re: ResponseEventExt) {
        method = RequestEnum.ACK
        serverAddress = re.remoteIpAddress.toString()
        serverPort = re.remotePort
        re.response.headerNames.forEach {
            when (it.toString()) {
                SIPHeaderNames.VIA -> {
                    val via = re.response.getHeader(it.toString()) as Via
                    localIpAddress = via.sentBy.host.address
                    localPort = via.sentBy.port
                    branchName = via.branch
                }
                SIPHeaderNames.CALL_ID -> {
                    val header = re.response.getHeader(it.toString()) as CallID
                    callId = header.callId
                }
                SIPHeaderNames.CSEQ -> {
                    val header = re.response.getHeader(it.toString()) as CSeq
                    sequenceNumber = header.seqNumber
                }
                SIPHeaderNames.FROM -> {
                    val header = re.response.getHeader(it.toString()) as From
                    userLogin = header.userAtHostPort.split("@")[0]
                    userPassword = "**********"
                    fromHeaderTag = header.tag
                }
                SIPHeaderNames.WWW_AUTHENTICATE -> {
                    val header = re.response.getHeader(it.toString()) as WWWAuthenticate
                    authorization.type = AuthMethodEnum.valueOf(header.scheme.toUpperCase())
//                    header.parameters.forEach { k, v ->
//                        when(k) {
//                            "algorithm" -> authorization.algorithm =
//                        }
//                    }
                }
            }
        }
    }

    private fun getRequestURI(): URI {
        return SipProtocol.addressFactory.createURI("sip:$serverAddress:$serverPort")
    }
}