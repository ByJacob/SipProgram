package pl.edu.pwr.weka.sipprogram.gui.model.header

import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class HeaderFrom {
    val userProperty = SimpleStringProperty()
    var user by userProperty

    val addressProperty = SimpleStringProperty()
    var address by addressProperty

    val tagProperty = SimpleStringProperty()
    var tag by tagProperty

}

class HeaderFromModel(headerFrom: HeaderFrom) : ViewModel() {
    constructor() : this(HeaderFrom())

    val user = bind(true) { headerFrom.userProperty }
    val address = bind(true) { headerFrom.addressProperty }
    val tag = bind(true) { headerFrom.tagProperty }
}
