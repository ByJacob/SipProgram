package pl.edu.pwr.weka.sipprogram.gui2.model

import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 29.10.2018 20:48
 */

class ProcessConnection {
    val listFormRequestProperty = SimpleListProperty<FormRequestModel>(FXCollections.observableArrayList())
    var listFormRequest by listFormRequestProperty

    val selectedFormRequestProperty = SimpleObjectProperty<FormRequestModel>()
    var selectedFormRequest by selectedFormRequestProperty
}

class ProcessConnectionModel(val processConnection: ProcessConnection) : ViewModel() {
    val listFormRequest = bind{processConnection.listFormRequestProperty}
    val selectedFormRequest = bind{processConnection.selectedFormRequestProperty}
}
