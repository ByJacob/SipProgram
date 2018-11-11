package pl.edu.pwr.weka.sipprogram.gui.model.header

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class HeaderTo {
    val userProperty = SimpleStringProperty("")
    var user by userProperty

    val addressProperty = SimpleStringProperty("")
    var address by addressProperty

    val portProperty = SimpleIntegerProperty(0)
    var port by portProperty

}

class HeaderToModel(headerTo: HeaderTo) : ViewModel() {
    constructor() : this(HeaderTo())

    val user = bind(true) { headerTo.userProperty }
    val address = bind(true) { headerTo.addressProperty }
    val port = bind(true) {headerTo.portProperty}
}
