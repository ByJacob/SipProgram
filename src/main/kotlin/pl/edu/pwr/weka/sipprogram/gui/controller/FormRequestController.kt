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
        val sipResponse = responseEventExt.response as SIPResponse

        val headerRequestLineRowView = HeaderStatusLineRowView()
        headerRequestLineRowView.controller.model.headerStatusLineRow.method = RequestEnum.ACK
        headerRequestLineRowView.controller.model.headerStatusLineRow.statusCode = sipResponse.statusLine.statusCode
        headerRequestLineRowView.controller.model.headerStatusLineRow.message = sipResponse.statusLine.reasonPhrase
        model.method.bind(headerRequestLineRowView.controller.model.method)
        model.statusCode.bind(headerRequestLineRowView.controller.model.statusCode)
        model.messageStatusCode.bind(headerRequestLineRowView.controller.model.message)
        listHeaderRowsView.add(headerRequestLineRowView)

        val headerFromRowView = HeaderFromRowView()
        headerFromRowView.controller.model.headerFrom.port = (sipResponse.fromHeader.address as AddressImpl).port
        headerFromRowView.controller.model.headerFrom.user = ((sipResponse.fromHeader.address as AddressImpl).uri as SipUri).user
        headerFromRowView.controller.model.headerFrom.address = (sipResponse.fromHeader.address as AddressImpl).host
        headerFromRowView.controller.model.headerFrom.tag = sipResponse.fromHeader.getParameter("tag")
        listHeaderRowsView.add(headerFromRowView)

        val headerToRowView = HeaderToRowView()
        headerToRowView.controller.model.headerTo.port = (sipResponse.toHeader.address as AddressImpl).port
        headerToRowView.controller.model.headerTo.user = ((sipResponse.toHeader.address as AddressImpl).uri as SipUri).user
        headerToRowView.controller.model.headerTo.address = (sipResponse.toHeader.address as AddressImpl).host
        headerToRowView.controller.model.headerTo.tag = sipResponse.toHeader.getParameter("tag")
        listHeaderRowsView.add(headerToRowView)
        sipResponse.headers.forEach { header ->
            when (header) {
                is ViaList -> {
                    header.forEach {
                        val headerViaRowView = HeaderViaRowView()
                        headerViaRowView.controller.model.headerVia.protocol = TransportProtocol.valueOf(
                                it.sentProtocol.transport
                        )
                        headerViaRowView.controller.model.headerVia.address = it.sentBy.host.hostname
                        headerViaRowView.controller.model.headerVia.port = it.sentBy.port
                        headerViaRowView.controller.model.headerVia.branch = it.getParameter("branch")
                        listHeaderRowsView.add(headerViaRowView)
                    }
                }
            }
        }

        val headerCallIdRowView = HeaderCallIdRowView()
        headerCallIdRowView.controller.model.headerCallId.callId = sipResponse.callIdHeader.callId
        listHeaderRowsView.add(headerCallIdRowView)

        val headerCSeqRowView = HeaderCSeqRowView()
        headerCSeqRowView.controller.model.headerCSeq.method = RequestEnum.valueOf(sipResponse.cSeqHeader.method)
        headerCSeqRowView.controller.model.headerCSeq.number = sipResponse.cSeqHeader.seqNumber.toInt()
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
                    headerServerRowView.controller.model.headerServer.server = listProduct.joinToString()
                    listHeaderRowsView.add(headerServerRowView)
                }
                is AllowList -> {
                    val headerAllowRowView = HeaderAllowRowView()
                    header.forEach {
                        headerAllowRowView.controller.model.allowList.add(RequestEnum.valueOf(it.method))
                    }
                    listHeaderRowsView.add(headerAllowRowView)
                }
                is SupportedList -> {
                    val headerSupportedRowView = HeaderSupportedRowView()
                    header.forEach {
                        headerSupportedRowView.controller.model.supportedList
                                .add(SupportedEnum.valueOf(it.optionTag.toUpperCase()))
                    }
                    listHeaderRowsView.add(headerSupportedRowView)
                }
                is WWWAuthenticateList -> {
                    header.forEach {
                        val headerWWWAuthenticateRowView = HeaderWWWAuthenticateRowView()
                        headerWWWAuthenticateRowView.controller.model.headerWWWAuthenticate.scheme =
                                AuthSchemeEnum.valueOf(it.scheme.toUpperCase())
                        println(it.getParameter("algorithm"))
                        headerWWWAuthenticateRowView.controller.model.headerWWWAuthenticate.algorithm =
                                AlgorithmEnum.valueOf(it.getParameter("algorithm").toUpperCase().replace("-", ""))
                        headerWWWAuthenticateRowView.controller.model.headerWWWAuthenticate.realm =
                                it.getParameter("realm")
                        headerWWWAuthenticateRowView.controller.model.headerWWWAuthenticate.nonce =
                                it.getParameter("nonce")
                        listHeaderRowsView.add(headerWWWAuthenticateRowView)
                    }
                }
                is Expires -> {
                    val headerExpiresRowView = HeaderExpiresRowView()
                    headerExpiresRowView.controller.model.headerExpires.expiresd = header.expires
                    listHeaderRowsView.add(headerExpiresRowView)
                }
                is ContactList -> {
                    header.forEach {
                        val headerContactRowView = HeaderContactRowView()
                        val headerContact = headerContactRowView.controller.model.headerContact
                        headerContact.port = (it.address as AddressImpl).port
                        headerContact.user = ((sipResponse.fromHeader.address as AddressImpl).uri as SipUri).user
                        headerContact.address = (sipResponse.fromHeader.address as AddressImpl).host
                        headerContact.expires = it.getParameter("expires").toInt()
                        listHeaderRowsView.add(headerContactRowView)
                    }
                }
                is SIPDateHeader -> {

                }
            }
        }
        model.formRequest.isSendingRequest = false
    }
}
