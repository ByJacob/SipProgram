package pl.edu.pwr.weka.sipprogram.gui.view

import com.sun.prism.paint.Color
import javafx.scene.control.TreeItem
import javafx.scene.paint.Paint
import pl.edu.pwr.weka.sipprogram.gui.controller.DictionaryController
import pl.edu.pwr.weka.sipprogram.gui.controller.DictionaryItem
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 08.12.2018 14:42
 */
class DictionaryView : View(FX.messages["dictionary"]) {

    val controller: DictionaryController by inject()

    override val root = borderpane {
        messages = controller.messages
        left {
            val types = controller.dictionaryList
                .filter { it.itemType.isNotEmpty() }
                .map { it.itemType }
                .distinct().map { DictionaryItem(it) }
            treeview<DictionaryItem>(TreeItem(DictionaryItem("Zagadnienia"))) {
                root.isExpanded = true
                cellFormat {
                    text = if (it.name.isEmpty()) it.itemType.toString()
                    else it.name
                }
                onUserSelect {
                    if (it.name.isNotEmpty()) {
                        controller.showSelectedItem(it)
                    }
                }
                populate { parent ->
                    when {
                        parent == root -> types
                        parent.value.name.isEmpty() ->
                            controller.dictionaryList
                                .filter { it.itemType == parent.value.itemType }
                                .sortedBy { it.name }
                        parent.value.name.isNotEmpty() ->
                            emptyList()
                        else -> null
                    }
                }
            }
        }
        center {
            vbox {
                spacing = 30.0
                style {
                    padding = box(10.px)
                }
                label {
                    bind(controller.model.name)
                    style {
                        fontSize = 25.px
                    }
                }
                textarea {
                    bind(controller.model.description)
                    isEditable = false
                    isWrapText = true
                    style {
                        fontSize = 18.px
                        backgroundColor += Paint.valueOf("#00000000")
                    }
                    stylesheet {
                        Stylesheet.content {
                            backgroundColor += Paint.valueOf("#00000000")
                        }
                        Stylesheet.focused {
                            backgroundColor += Paint.valueOf("#00000000")
                        }
                    }
                }
//                label {
//                    bind(controller.model.description)
//                    isWrapText = true
//                    style {
//                        fontSize = 18.px
//                    }
//                }
                textflow {
                    visibleWhen { controller.model.url.isNotBlank() }
                    val test = messages["more_information"]
                    text(messages["more_information"]) {
                        style {
                            fontSize = 15.px
                        }
                    }
                    hyperlink(controller.model.urlName) {
                        style {
                            fontSize = 15.px
                        }
                        action {
                            if (controller.model.url.value.isNotEmpty())
                                hostServices.showDocument(controller.model.url.value)
                        }
                    }
                }
            }
        }
    }
}
