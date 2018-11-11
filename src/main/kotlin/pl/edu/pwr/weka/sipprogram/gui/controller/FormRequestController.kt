package pl.edu.pwr.weka.sipprogram.gui.controller

import gov.nist.javax.sip.header.SIPHeader
import gov.nist.javax.sip.message.SIPRequest
import javafx.collections.FXCollections
import javafx.collections.ListChangeListener
import javafx.collections.ObservableList
import javafx.scene.Node
import pl.edu.pwr.weka.sipprogram.gui.controller.header.HeaderRequestLineController
import pl.edu.pwr.weka.sipprogram.gui.view.row.AddHeaderRowView
import pl.edu.pwr.weka.sipprogram.gui.view.row.base.BaseHeaderView
import tornadofx.*


class FormRequestController : Controller() {

    lateinit var listHeaderRowsNode: ObservableList<Node>
    val listHeaderRowsView = FXCollections.observableArrayList<BaseHeaderView>()

    init {
        listHeaderRowsView.addListener { c: ListChangeListener.Change<out View> ->
            while (c.next()) {
                when {
                    c.wasPermutated() -> for (i in c.from until c.to) {
                        println("Permuted: " + i + " " + listHeaderRowsView[i])
                    }
                    c.wasUpdated() -> for (i in c.from until c.to) {
                        println("Updated: " + i + " " + listHeaderRowsView[i])
                    }
                    c.wasAdded() -> for (i in c.from until c.to) {
                        runAsync {
                            println("Added $i: " + listHeaderRowsView[i])
                        } ui {
                            listHeaderRowsNode.add(i, listHeaderRowsView[i].root)
                        }
                    }
                    else -> {
                        for (removedItem in c.removed) {
                            println("Removed: $removedItem")
                        }
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
}
