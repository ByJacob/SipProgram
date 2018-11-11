package pl.edu.pwr.weka.sipprogram.gui.model.header

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class HeaderContact() {
    val userProperty = SimpleStringProperty()
    var user by userProperty

    val passwordProperty = SimpleStringProperty()
    var password by passwordProperty

    val portProperty = SimpleIntegerProperty()
    var port by portProperty

}

class HeaderContactModel(headerContact: HeaderContact) : ViewModel() {
    constructor() : this(HeaderContact())

    val user = bind(true) { headerContact.userProperty }
    val password = bind(true) { headerContact.passwordProperty }
    val port = bind(true) { headerContact.portProperty }
}
