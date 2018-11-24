package pl.edu.pwr.weka.sipprogram.gui.controller

import gov.nist.javax.sip.ResponseEventExt
import gov.nist.javax.sip.address.AddressImpl
import gov.nist.javax.sip.address.SipUri
import gov.nist.javax.sip.header.*
import gov.nist.javax.sip.message.SIPRequest
import gov.nist.javax.sip.message.SIPResponse
import javafx.collections.FXCollections
import javafx.collections.ListChangeListener
import javafx.collections.ObservableList
import javafx.scene.Node
import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderRequestLineController
import pl.edu.pwr.weka.sipprogram.gui.model.FormRequestModel
import pl.edu.pwr.weka.sipprogram.gui.view.row.AddHeaderRowView
import pl.edu.pwr.weka.sipprogram.gui.view.row.base.BaseHeaderView
import pl.edu.pwr.weka.sipprogram.gui.view.row.header.*
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.*
import tornadofx.*


class FormRequestController : Controller() {


    val listHeaderRowsView = FXCollections.observableArrayList<BaseHeaderView>()

    lateinit var listHeaderRowsNode: ObservableList<Node>
    lateinit var model: FormRequestModel

    init {
        listHeaderRowsView.addListener { c: ListChangeListener.Change<out BaseHeaderView> ->
            while (c.next()) {
                when {
                    c.wasPermutated() -> for (i in c.from until c.to) {
                        println("Permuted: " + i + " " + listHeaderRowsView[i])
                    }
                    c.wasUpdated() -> for (i in c.from until c.to) {
                        println("Updated: " + i + " " + listHeaderRowsView[i])
                    }
                    c.wasAdded() -> for (i in c.from until c.to) {
                        runAsync {} ui {
                            if (listHeaderRowsView.lastIndex >= i)
                                listHeaderRowsNode.add(i, listHeaderRowsView[i].root)
                        }
                    }
                    c.wasRemoved() -> for (removedItem in c.removed) {
                        runAsync {} ui {
                            if (removedItem.root in listHeaderRowsNode)
                                listHeaderRowsNode.remove(removedItem.root)
                        }
                    }
                    else -> {
                        println("Do nothing")
                    }
                }
            }
        }

        subscribe<AddHeaderRowView.AddNewHeaderToFormEvent> {
            val view = (it.header.node.newInstance() as BaseHeaderView)
            listHeaderRowsView.add(view)
        }
    }

    fun createRequest(): SIPRequest {
        val toSipRequest = (listHeaderRowsView[0].controller as HeaderRequestLineController).toSipRequest()

        listHeaderRowsView.forEachIndexed { index, baseHeaderView ->
            if (index > 0)
                toSipRequest.attachHeader(baseHeaderView.controller.toSipHeader() as SIPHeader, true, false)
        }
        return toSipRequest
    }

    fun processResponseEventExt(responseEventExt: ResponseEventExt) {

        listHeaderRowsNode.clear()
        listHeaderRowsView.clear()
        model.formRequest.method = RequestEnum.ACK
        val headerRequestLineRowView = HeaderRequestLineRowView()
        headerRequestLineRowView.model.headerRequestLineRow.method = RequestEnum.ACK
        headerRequestLineRowView.model.headerRequestLineRow.requestHost = responseEventExt.remoteIpAddress
        headerRequestLineRowView.model.headerRequestLineRow.requestPort = responseEventExt.remotePort
        listHeaderRowsView.add(headerRequestLineRowView)
        val sipResponse = responseEventExt.response as SIPResponse

        val headerFromRowView = HeaderFromRowView()
        headerFromRowView.model.headerFrom.port = (sipResponse.fromHeader.address as AddressImpl).port
        headerFromRowView.model.headerFrom.user = ((sipResponse.fromHeader.address as AddressImpl).uri as SipUri).user
        headerFromRowView.model.headerFrom.address = (sipResponse.fromHeader.address as AddressImpl).host
        headerFromRowView.model.headerFrom.tag = sipResponse.fromHeader.getParameter("tag")
        listHeaderRowsView.add(headerFromRowView)

        val headerToRowView = HeaderToRowView()
        headerToRowView.model.headerTo.port = (sipResponse.toHeader.address as AddressImpl).port
        headerToRowView.model.headerTo.user = ((sipResponse.toHeader.address as AddressImpl).uri as SipUri).user
        headerToRowView.model.headerTo.address = (sipResponse.toHeader.address as AddressImpl).host
        headerToRowView.model.headerTo.tag = sipResponse.toHeader.getParameter("tag")
        listHeaderRowsView.add(headerToRowView)
        sipResponse.headers.forEach { header ->
            when (header) {
                is ViaList -> {
                    header.forEach {
                        val headerViaRowView = HeaderViaRowView()
                        headerViaRowView.model.headerVia.protocol = TransportProtocol.valueOf(
                                it.sentProtocol.transport
                        )
                        headerViaRowView.model.headerVia.address = it.sentBy.host.hostname
                        headerViaRowView.model.headerVia.port = it.sentBy.port
                        headerViaRowView.model.headerVia.branch = it.getParameter("branch")
                        listHeaderRowsView.add(headerViaRowView)
                    }
                }
            }
        }

        val headerCallIdRowView = HeaderCallIdRowView()
        headerCallIdRowView.model.headerCallId.callId = sipResponse.callIdHeader.callId
        listHeaderRowsView.add(headerCallIdRowView)

        val headerCSeqRowView = HeaderCSeqRowView()
        headerCSeqRowView.model.headerCSeq.method = RequestEnum.valueOf(sipResponse.cSeqHeader.method)
        headerCSeqRowView.model.headerCSeq.number = sipResponse.cSeqHeader.seqNumber.toInt()
        listHeaderRowsView.add(headerCSeqRowView)
        sipResponse.headers.forEach { header ->
            when (header) {
                is ContentLength -> println("NOT IMPLEMENTED!!!!!!!!!!!")
                is Server -> {
                    val headerServerRowView = HeaderServerRowView()
                    val listProduct = mutableListOf<String>()
                    val iteratorListProduct = header.product
                    while (iteratorListProduct.hasNext()) {
                        listProduct.add(iteratorListProduct.next() as String)
                    }
                    headerServerRowView.model.headerServer.server = listProduct.joinToString()
                    listHeaderRowsView.add(headerServerRowView)
                }
                is AllowList -> {
                    val headerAllowRowView = HeaderAllowRowView()
                    header.forEach {
                        headerAllowRowView.model.allowList.add(RequestEnum.valueOf(it.method))
                    }
                    listHeaderRowsView.add(headerAllowRowView)
                }
                is SupportedList -> {
                    val headerSupportedRowView = HeaderSupportedRowView()
                    header.forEach {
                        headerSupportedRowView.model.supportedList.add(SupportedEnum.valueOf(it.optionTag.toUpperCase()))
                    }
                    listHeaderRowsView.add(headerSupportedRowView)
                }
                is WWWAuthenticateList -> {
                    header.forEach {
                        val headerWWWAuthenticateRowView = HeaderWWWAuthenticateRowView()
                        headerWWWAuthenticateRowView.model.headerWWWAuthenticate.scheme = AuthSchemeEnum.valueOf(it.scheme.toUpperCase())
                        println(it.getParameter("algorithm"))
                        headerWWWAuthenticateRowView.model.headerWWWAuthenticate.algorithm = AlgorithmEnum.valueOf(it.getParameter("algorithm").toUpperCase().replace("-", ""))
                        headerWWWAuthenticateRowView.model.headerWWWAuthenticate.realm = it.getParameter("realm")
                        headerWWWAuthenticateRowView.model.headerWWWAuthenticate.nonce = it.getParameter("nonce")
                        listHeaderRowsView.add(headerWWWAuthenticateRowView)
                    }
                }
            }
        }
        model.formRequest.isSendingRequest = false
    }
}
