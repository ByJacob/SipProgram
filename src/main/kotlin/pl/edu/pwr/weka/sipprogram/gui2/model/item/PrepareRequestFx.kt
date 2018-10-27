package pl.edu.pwr.weka.sipprogram.gui2.model.item

import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.scene.layout.StackPane
import tornadofx.getValue
import tornadofx.setValue

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 23.10.2018 23:01
 */
class PrepareRequestFx(listId: MutableList<StackPane>) {

    val selectedStackPaneProperty = SimpleObjectProperty<StackPane>(if (listId.size > 0) listId[0] else StackPane())
    var selectedStackPane by selectedStackPaneProperty


}