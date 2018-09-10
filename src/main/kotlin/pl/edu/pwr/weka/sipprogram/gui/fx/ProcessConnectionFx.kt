package pl.edu.pwr.weka.sipprogram.gui.fx

import javafx.beans.property.SimpleMapProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 07.09.2018 23:17
 */
class ProcessConnectionFx {
    val serverAddressPort = SimpleStringProperty()
    val localAddressPort = SimpleStringProperty()
    val user = SimpleStringProperty()
    val password = SimpleStringProperty()
}