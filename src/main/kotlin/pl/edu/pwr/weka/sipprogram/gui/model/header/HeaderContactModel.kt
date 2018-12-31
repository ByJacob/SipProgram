package pl.edu.pwr.weka.sipprogram.gui.model.header

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class HeaderContact {
    val userProperty = SimpleStringProperty("")
    var user by userProperty

    val addressProperty = SimpleStringProperty("")
    var address by addressProperty

    val portProperty = SimpleIntegerProperty(0)
    var port by portProperty

    val expiresProperty = SimpleIntegerProperty(1200)
    var expires by expiresProperty


}

class HeaderContactModel(val headerContact: HeaderContact) : ViewModel() {
    constructor() : this(HeaderContact())

    val user = bind(true) { headerContact.userProperty }
    val address = bind(true) { headerContact.addressProperty }
    val port = bind(true) { headerContact.portProperty }
    val expires = bind(true){headerContact.expiresProperty}
}
