package pl.edu.pwr.weka.sipprogram.gui.controller

import javafx.collections.FXCollections
import javafx.collections.ListChangeListener
import javafx.collections.ObservableList
import javafx.scene.Node
import pl.edu.pwr.weka.sipprogram.gui.view.row.AddHeaderRowView
import tornadofx.*


class FormRequestController : Controller() {

    lateinit var listHeaderRowsNode: ObservableList<Node>
    val listHeaderRowsView = FXCollections.observableArrayList<View>()

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
                            if (i > 1)
                                listHeaderRowsNode.add(i - 1, listHeaderRowsView[i].root)
                            else
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
            val view = (it.header.node.newInstance() as View)
            listHeaderRowsView.add(view)
        }
    }
}
