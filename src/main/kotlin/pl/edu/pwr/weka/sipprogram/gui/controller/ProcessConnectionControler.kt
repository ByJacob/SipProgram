package pl.edu.pwr.weka.sipprogram.gui.controller

import gov.nist.javax.sip.ResponseEventExt
import javafx.concurrent.Task
import javafx.util.Duration
import pl.edu.pwr.weka.sipprogram.gui.view.fragment.FormRequestFragment
import pl.edu.pwr.weka.sipprogram.gui.view.ProcessConnectionView
import pl.edu.pwr.weka.sipprogram.gui.view.header.*
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.AlgorithmEnum
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.AuthSchemeEnum
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.RequestEnum
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.TransportProtocol
import pl.edu.pwr.weka.sipprogram.sip.request.base.ResponseListener
import tornadofx.*
import javax.sip.ResponseEvent

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 16:59
 */
class ProcessConnectionController : Controller() {

    val formRequestFragmentList = mutableListOf<FormRequestFragment>().observable()

    private var tryCount = 0

    private val responseAction = { index: Int, responseEventEx: ResponseEventExt ->
        runAsync {
            val responseFragment = find<FormRequestFragment>(Scope())
            try {
                responseFragment.controller.processResponseEventExt(responseEventEx)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            var isNeedRepeat = false
            val responseNonce = getFormNonce(responseFragment)
            if (responseNonce.isNotEmpty() && isExistAck401() && tryCount < 5) {
                setFormNonce(formRequestFragmentList[index - 1], responseNonce)
                isNeedRepeat = true
                tryCount++
            }
            Pair(responseFragment, isNeedRepeat)
        } ui {
            if (it.second) {
                sendRequest(index - 1)
            } else {
                formRequestFragmentList.add(index, it.first)
                sendRequest(index + 1)
                tryCount = 0
            }
        }
    }

    init {
        subscribe<RemoveFormRequestEvent> {
            val indexOf = formRequestFragmentList.indexOf(it.fragment)
            formRequestFragmentList.remove(it.fragment)
            when {
                indexOf < formRequestFragmentList.size ->
                    fire(ProcessConnectionView.OpenFormRequestEvent(indexOf))
                indexOf >= formRequestFragmentList.size && formRequestFragmentList.lastIndex >= 0 ->
                    fire(ProcessConnectionView.OpenFormRequestEvent(formRequestFragmentList.lastIndex))
            }
            fire(ProcessConnectionView.SelectElementInListEvent(indexOf))
        }

        createExampleDate()
    }

    private fun createExampleDate() {
        val register1 = find<FormRequestFragment>(Scope())
        register1.controller.let {controller ->
            controller.listHeaderRowsNode.clear()
            controller.listHeaderRowsView.clear()
            val headerRequestLineRowView = HeaderRequestLineRowView()
            headerRequestLineRowView.controller.model.headerRequestLineRow.let {
                it.method = RequestEnum.REGISTER
                it.requestHost = "ss2.biloxi.example.com"
                it.requestPort = 5060
            }
            register1.model.method.bind(headerRequestLineRowView.controller.model.method)
            controller.addHeader(headerRequestLineRowView)
            val headerViaRowView = HeaderViaRowView()
            headerViaRowView.controller.model.headerVia.let {
                it.address = "client.biloxi.example.com"
                it.branch = "z9hG4bKnashds7"
                it.port = 5061
                it.protocol = TransportProtocol.TCP
            }
            controller.addHeader(headerViaRowView)
            val headerMaxForwardsRowView = HeaderMaxForwardsRowView()
            headerMaxForwardsRowView.controller.model.headerMaxForwards.let {
                it.maxForwards = 70
            }
            controller.addHeader(headerMaxForwardsRowView)
            val headerFromRowView = HeaderFromRowView()
            headerFromRowView.controller.model.headerFrom.let {
                it.address = "biloxi.example.com"
                it.port = 5060
                it.tag = "a73kszlfl"
                it.user = "bob"
            }
            controller.addHeader(headerFromRowView)
            val headerToRowView = HeaderToRowView()
            headerToRowView.controller.model.headerTo.let {
                it.address = "biloxi.example.com"
                it.port = 5060
                it.user = "bob"
            }
            controller.addHeader(headerToRowView)
            val headerCallIdRowView = HeaderCallIdRowView()
            headerCallIdRowView.controller.model.headerCallId.let {
                it.callId = "1j9FpLxk3uxtm8tn@biloxi.example.com"
            }
            controller.addHeader(headerCallIdRowView)
            val headerCSeqRowView = HeaderCSeqRowView()
            headerCSeqRowView.controller.model.headerCSeq.let {
                it.method = RequestEnum.REGISTER
                it.number = 1
            }
            controller.addHeader(headerCSeqRowView)
            val headerContactRowView = HeaderContactRowView()
            headerContactRowView.controller.model.headerContact.let {
                it.address = "client.biloxi.example.com"
                it.port = 5060
                it.user = "bob"
                it.expires = 3600
            }
            controller.addHeader(headerContactRowView)
        }
        formRequestFragmentList.add(register1)

        val ack407 = find<FormRequestFragment>(Scope())
        ack407.controller.let { controller ->
            controller.listHeaderRowsNode.clear()
            controller.listHeaderRowsView.clear()
            val headerRequestLineRowView = HeaderStatusLineRowView()
            headerRequestLineRowView.controller.model.headerStatusLineRow.let {
                it.method = RequestEnum.ACK
                it.statusCode = 401
                it.message = "Unauthorized"
            }
            ack407.model.method.bind(headerRequestLineRowView.controller.model.method)
            ack407.model.statusCode.bind(headerRequestLineRowView.controller.model.statusCode)
            ack407.model.messageStatusCode.bind(headerRequestLineRowView.controller.model.message)
            ack407.model.formRequest.isSendingRequest = false
            controller.addHeader(headerRequestLineRowView)
            val headerViaRowView = HeaderViaRowView()
            headerViaRowView.controller.model.headerVia.let {
                it.address = "client.biloxi.example.com"
                it.branch = "z9hG4bKnashds7"
                it.port = 5061
                it.protocol = TransportProtocol.TCP
            }
            controller.addHeader(headerViaRowView)
            val headerFromRowView = HeaderFromRowView()
            headerFromRowView.controller.model.headerFrom.let {
                it.address = "biloxi.example.com"
                it.port = 5060
                it.tag = "a73kszlfl"
                it.user = "bob"
            }
            controller.addHeader(headerFromRowView)
            val headerToRowView = HeaderToRowView()
            headerToRowView.controller.model.headerTo.let {
                it.address = "biloxi.example.com"
                it.port = 5060
                it.user = "bob"
                it.tag = "1410948204"
            }
            controller.addHeader(headerToRowView)
            val headerCallIdRowView = HeaderCallIdRowView()
            headerCallIdRowView.controller.model.headerCallId.let {
                it.callId = "1j9FpLxk3uxtm8tn@biloxi.example.com"
            }
            controller.addHeader(headerCallIdRowView)
            val headerCSeqRowView = HeaderCSeqRowView()
            headerCSeqRowView.controller.model.headerCSeq.let {
                it.method = RequestEnum.REGISTER
                it.number = 1
            }
            controller.addHeader(headerCSeqRowView)
            val headerWWWAuthenticateRowView = HeaderWWWAuthenticateRowView()
            headerWWWAuthenticateRowView.controller.model.headerWWWAuthenticate.let {
                it.scheme = AuthSchemeEnum.DIGEST
                it.realm = "atlanta.example.com"
                it.algorithm = AlgorithmEnum.MD5
                it.nonce = "ea9c8e88df84f1cec4341ae6cbe5a359"
            }
            controller.addHeader(headerWWWAuthenticateRowView)
        }
        formRequestFragmentList.add(ack407)

        val register2 = find<FormRequestFragment>(Scope())
        register2.controller.let { controller ->
            controller.listHeaderRowsNode.clear()
            controller.listHeaderRowsView.clear()
            val headerRequestLineRowView = HeaderRequestLineRowView()
            headerRequestLineRowView.controller.model.headerRequestLineRow.let {
                it.method = RequestEnum.REGISTER
                it.requestHost = "ss2.biloxi.example.com"
                it.requestPort = 5060
            }
            register2.model.method.bind(headerRequestLineRowView.controller.model.method)
            controller.addHeader(headerRequestLineRowView)
            val headerViaRowView = HeaderViaRowView()
            headerViaRowView.controller.model.headerVia.let {
                it.address = "client.biloxi.example.com"
                it.branch = "z9hG4bKnashds7"
                it.port = 5061
                it.protocol = TransportProtocol.TCP
            }
            controller.addHeader(headerViaRowView)
            val headerMaxForwardsRowView = HeaderMaxForwardsRowView()
            headerMaxForwardsRowView.controller.model.headerMaxForwards.let {
                it.maxForwards = 70
            }
            controller.addHeader(headerMaxForwardsRowView)
            val headerFromRowView = HeaderFromRowView()
            headerFromRowView.controller.model.headerFrom.let {
                it.address = "biloxi.example.com"
                it.port = 5060
                it.tag = "ja743ks76zlflH"
                it.user = "bob"
            }
            controller.addHeader(headerFromRowView)
            val headerToRowView = HeaderToRowView()
            headerToRowView.controller.model.headerTo.let {
                it.address = "biloxi.example.com"
                it.port = 5060
                it.user = "bob"
            }
            controller.addHeader(headerToRowView)
            val headerCallIdRowView = HeaderCallIdRowView()
            headerCallIdRowView.controller.model.headerCallId.let {
                it.callId = "1j9FpLxk3uxtm8tn@biloxi.example.com"
            }
            controller.addHeader(headerCallIdRowView)
            val headerCSeqRowView = HeaderCSeqRowView()
            headerCSeqRowView.controller.model.headerCSeq.let {
                it.method = RequestEnum.REGISTER
                it.number = 2
            }
            controller.addHeader(headerCSeqRowView)
            val headerContactRowView = HeaderContactRowView()
            headerContactRowView.controller.model.headerContact.let {
                it.address = "client.biloxi.example.com"
                it.port = 5060
                it.user = "bob"
                it.expires = 3600
            }
            controller.addHeader(headerContactRowView)

            val headerAuthorizationRowView = HeaderAuthorizationRowView()
            headerAuthorizationRowView.controller.model.headerAuthorization.let {
                it.authenticationUriHost = "client.biloxi.example.com"
                it.method = RequestEnum.REGISTER
                it.password = "password"
                it.username = "bob"
                it.algorithm = AlgorithmEnum.MD5
                it.nonce = "ea9c8e88df84f1cec4341ae6cbe5a359"
                it.realm = "atlanta.example.com"
            }
            controller.addHeader(headerAuthorizationRowView)
        }
        formRequestFragmentList.add(register2)

        val ack200 = find<FormRequestFragment>(Scope())
        ack200.controller.let { controller ->
            controller.listHeaderRowsNode.clear()
            controller.listHeaderRowsView.clear()
            val headerRequestLineRowView = HeaderStatusLineRowView()
            headerRequestLineRowView.controller.model.headerStatusLineRow.let {
                it.method = RequestEnum.ACK
                it.statusCode = 200
                it.message = "OK"
            }
            ack200.model.method.bind(headerRequestLineRowView.controller.model.method)
            ack200.model.statusCode.bind(headerRequestLineRowView.controller.model.statusCode)
            ack200.model.messageStatusCode.bind(headerRequestLineRowView.controller.model.message)
            ack200.model.formRequest.isSendingRequest = false
            controller.addHeader(headerRequestLineRowView)
            val headerViaRowView = HeaderViaRowView()
            headerViaRowView.controller.model.headerVia.let {
                it.address = "client.biloxi.example.com"
                it.branch = "z9hG4bKnashds7"
                it.port = 5061
                it.protocol = TransportProtocol.TCP
            }
            controller.addHeader(headerViaRowView)
            val headerFromRowView = HeaderFromRowView()
            headerFromRowView.controller.model.headerFrom.let {
                it.address = "biloxi.example.com"
                it.port = 5060
                it.tag = "ja743ks76zlflH"
                it.user = "bob"
            }
            controller.addHeader(headerFromRowView)
            val headerToRowView = HeaderToRowView()
            headerToRowView.controller.model.headerTo.let {
                it.address = "biloxi.example.com"
                it.port = 5060
                it.user = "bob"
                it.tag = "37GkEhwl6"
            }
            controller.addHeader(headerToRowView)
            val headerCallIdRowView = HeaderCallIdRowView()
            headerCallIdRowView.controller.model.headerCallId.let {
                it.callId = "1j9FpLxk3uxtm8tn@biloxi.example.com"
            }
            controller.addHeader(headerCallIdRowView)
            val headerCSeqRowView = HeaderCSeqRowView()
            headerCSeqRowView.controller.model.headerCSeq.let {
                it.method = RequestEnum.REGISTER
                it.number = 2
            }
            controller.addHeader(headerCSeqRowView)
        }
        formRequestFragmentList.add(ack200)

        runLater(Duration.seconds(2.0)) {
            fire(ProcessConnectionView.OpenFormRequestEvent(0))
            fire(ProcessConnectionView.SelectElementInListEvent(0))
        }
    }

    private fun isExistAck401(): Boolean {
        return formRequestFragmentList.any {
            it.controller.model.formRequest.method == RequestEnum.ACK &&
                    it.controller.model.formRequest.statusCode == 401
        }
    }

    private fun getFormNonce(responseFragment: FormRequestFragment): String {
        responseFragment.controller.listHeaderRowsView.forEach {
            when (it) {
                is HeaderWWWAuthenticateRowView -> {
                    return it.controller.model.headerWWWAuthenticate.nonce
                }
                is HeaderAuthorizationRowView -> {
                    return it.controller.model.headerAuthorization.nonce
                }
            }
        }
        return ""
    }

    private fun setFormNonce(requestFragment: FormRequestFragment, newNonce: String) {
        requestFragment.controller.listHeaderRowsView.forEach {
            when (it) {
                is HeaderAuthorizationRowView -> {
                    it.controller.model.nonce.value = newNonce
                }
                is HeaderWWWAuthenticateRowView -> {
                    it.controller.model.nonce.value = newNonce
                }
            }
        }
    }

    fun startSendAll() {
        SipProtocol.sipClient.listeners.clear()
        formRequestFragmentList.removeAll(formRequestFragmentList.filter { !it.model.isSendingRequest.value })
        sendRequest(0)
    }

    fun startSendLast() {
        SipProtocol.sipClient.listeners.clear()
        if (!formRequestFragmentList.last().model.isSendingRequest.value)
            formRequestFragmentList.remove(formRequestFragmentList.last())
        sendRequest(formRequestFragmentList.lastIndex)
    }

    private fun sendRequest(index: Int) {
        if (formRequestFragmentList.lastIndex >= index) {
            runAsync {
                val createRequest = formRequestFragmentList[index].controller.createRequest()
                SipProtocol.sipClient.listeners.add(SipListenerImpl(index, responseAction))
                SipProtocol.sipProvider.sendRequest(createRequest)
                print(createRequest.toString())
            }
        }
    }

    class SipListenerImpl(var index: Int, var action: (Int, ResponseEventExt) -> Task<Pair<FormRequestFragment, Boolean>>) : ResponseListener {
        override fun processResponse(re: ResponseEvent) {
            action(index + 1, re as ResponseEventExt)
        }
    }

    class RemoveFormRequestEvent(val fragment: FormRequestFragment) : FXEvent()
}
