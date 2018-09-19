package pl.edu.pwr.weka.sipprogram.gui.fx

import javafx.beans.property.ListProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import pl.edu.pwr.weka.sipprogram.sip.request.base.RequestEnum

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 11.09.2018 18:41
 */
class FormRequestFx {
    val requestListProperty = SimpleListProperty<RequestEnum>(FXCollections.observableArrayList())
    val requestProperty = SimpleObjectProperty<RequestEnum>()
    val localAddressProperty = SimpleStringProperty()
    val localPortProperty = SimpleStringProperty()
    val serverAddressProperty = SimpleStringProperty()
    val serverPortProperty = SimpleStringProperty()
    val userProperty = SimpleStringProperty()
    val callIdProperty = SimpleStringProperty()
    val seqNumberProperty = SimpleStringProperty()
    val requestTxtStringProperty = SimpleStringProperty()
}