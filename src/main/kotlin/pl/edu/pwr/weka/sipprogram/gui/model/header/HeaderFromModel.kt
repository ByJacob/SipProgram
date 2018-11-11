package pl.edu.pwr.weka.sipprogram.gui.model.header

import javafx.beans.property.ReadOnlyIntegerProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import tornadofx.getValue
import tornadofx.setValue

class HeaderFrom {
    val userProperty = SimpleStringProperty("")
    var user by userProperty

    val addressProperty = SimpleStringProperty("")
    var address by addressProperty

    val portProperty = SimpleIntegerProperty(0)
    var port by portProperty


    val tagProperty = SimpleStringProperty("")
    var tag by tagProperty

}

class HeaderFromModel(headerFrom: HeaderFrom) : ViewModel() {
    constructor() : this(HeaderFrom())

    val user = bind(true) { headerFrom.userProperty }
    val address = bind(true) { headerFrom.addressProperty }
    val tag = bind(true) { headerFrom.tagProperty }
    val port = bind(true){headerFrom.portProperty}
}
