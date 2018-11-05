package pl.edu.pwr.weka.sipprogram.gui2.controller

import gov.nist.javax.sip.ResponseEventExt
import pl.edu.pwr.weka.sipprogram.gui2.model.FormRequestModel
import pl.edu.pwr.weka.sipprogram.gui2.model.ProcessConnection
import pl.edu.pwr.weka.sipprogram.gui2.model.ProcessConnectionModel
import pl.edu.pwr.weka.sipprogram.gui2.model.mapper.mapToRequest
import pl.edu.pwr.weka.sipprogram.gui2.view.ProcessConnectionView
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import pl.edu.pwr.weka.sipprogram.sip.request.Request
import pl.edu.pwr.weka.sipprogram.sip.request.base.ResponseListener
import tornadofx.*
import javax.sip.ResponseEvent

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 22.10.2018 22:00
 */

class ProcessConnectionController : Controller() {

    val model = ProcessConnectionModel(ProcessConnection())
    private val updateRequestList: (Int, FormRequestModel) -> Unit = { index, model ->
        fire(ProcessConnectionView.AddNewElementEvent(index, model))
    }

    init {

    }

    val createRequestForm: FormRequestModel
        get() {
            val cr = FormRequestModel(Request().mapToFormRequest())
            cr.formRequest.isSendingRequest = true
            return cr
        }
    val sendAllRequest = {
        val copyListRequest = model.listFormRequest.value.toList()
        copyListRequest.forEachIndexed { index, formRequestModel ->
            SipProtocol.sipClient.listeners.clear()
            SipProtocol.sipClient.listeners.add(ResponseListenerImpl((index * 2) + 1, updateRequestList))
            formRequestModel.formRequest.mapToRequest().sendRequest()
            Thread.sleep(1000)
        }
    }

    class ResponseListenerImpl(private val index: Int, private val action: (Int, FormRequestModel) -> Unit) : ResponseListener {

        override fun processResponse(re: ResponseEvent) {
            val request = Request()
            request.processResponse(re as ResponseEventExt)
            val model = FormRequestModel(request.mapToFormRequest())
            model.formRequest.isSendingRequest = false
            action(index, model)
        }

    }
}
