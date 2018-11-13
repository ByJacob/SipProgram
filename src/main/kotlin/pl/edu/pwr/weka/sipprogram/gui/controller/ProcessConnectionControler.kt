package pl.edu.pwr.weka.sipprogram.gui.controller

import gov.nist.javax.sip.ResponseEventExt
import javafx.concurrent.Task
import pl.edu.pwr.weka.sipprogram.gui.view.fragment.FormRequestFragment
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
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
            val controller = find<FormRequestFragment>(Scope())
            controller.controller.processResponseEventExt(responseEventEx)
            controller
        } ui {
            formRequestFragmentList.add(index, it)
            sendRequest(index + 1)
        }
    }

    fun startSendAll() {
        formRequestFragmentList.removeAll(formRequestFragmentList.filter { !it.model.isSendingRequest.value })
        sendRequest(0)
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

    class SipListenerImpl(var index: Int, var action: (Int, ResponseEventExt) -> Task<FormRequestFragment>) : ResponseListener {
        override fun processResponse(re: ResponseEvent) {
            action(index + 1, re as ResponseEventExt)
        }
    }
}
