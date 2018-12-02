package pl.edu.pwr.weka.sipprogram.gui.controller

import gov.nist.javax.sip.ResponseEventExt
import javafx.concurrent.Task
import pl.edu.pwr.weka.sipprogram.gui.view.FormRequestFragment
import pl.edu.pwr.weka.sipprogram.gui.view.header.HeaderAuthorizationRowView
import pl.edu.pwr.weka.sipprogram.gui.view.header.HeaderWWWAuthenticateRowView
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.RequestEnum
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
            val sendingNonce = getFormNonce(formRequestFragmentList[index - 1])
            val isPreviousAck =
                    if (formRequestFragmentList.size > 2 && index >=2 ) {
                        formRequestFragmentList[index - 2].model.method.value == RequestEnum.ACK &&
                        formRequestFragmentList[index - 2].model.statusCode.value == 401
                    } else {
                        false
                    }
            if (sendingNonce.isNotEmpty() && responseNonce.isNotEmpty() && sendingNonce != responseNonce && isPreviousAck) {
                setFormNonce(formRequestFragmentList[index - 1], responseNonce)
                setFormNonce(formRequestFragmentList[index - 2], responseNonce)
                isNeedRepeat = true
            }
            Pair(responseFragment, isNeedRepeat)
        } ui {
            if (it.second) {
                sendRequest(index - 1)
            } else {
                formRequestFragmentList.add(index, it.first)
                sendRequest(index + 1)
            }
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
}
