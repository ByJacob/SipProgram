package pl.edu.pwr.weka.sipprogram.gui.model.header

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import tornadofx.getValue
import tornadofx.setValue
import kotlin.random.Random

class HeaderTo {
    val userProperty = SimpleStringProperty("")
    var user by userProperty

    val addressProperty = SimpleStringProperty("")
    var address by addressProperty

    val portProperty = SimpleIntegerProperty(0)
    var port by portProperty

    val tagProperty = SimpleStringProperty(Random.nextLong(0, Long.MAX_VALUE).toString(16))
    var tag by tagProperty

}

class HeaderToModel(headerTo: HeaderTo) : ViewModel() {
    constructor() : this(HeaderTo())

    val user = bind(true) { headerTo.userProperty }
    val address = bind(true) { headerTo.addressProperty }
    val port = bind(true) {headerTo.portProperty}
    val tag = bind(true){headerTo.tagProperty}
}
