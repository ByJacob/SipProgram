package pl.edu.pwr.weka.sipprogram.gui.model.header

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import tornadofx.*
import tornadofx.getValue
import tornadofx.setValue
import java.util.*
import kotlin.random.Random

class HeaderFrom {
    val userProperty = SimpleStringProperty("")
    var user by userProperty

    val addressProperty = SimpleStringProperty(SipProtocol.ip)
    var address by addressProperty

    val portProperty = SimpleIntegerProperty(SipProtocol.port)
    var port by portProperty


    val tagProperty = SimpleStringProperty(Random.nextLong(0, Long.MAX_VALUE).toString(16))
    var tag by tagProperty

}

class HeaderFromModel(val headerFrom: HeaderFrom) : ViewModel() {
    constructor() : this(HeaderFrom())

    val user = bind(true) { headerFrom.userProperty }
    val address = bind(true) { headerFrom.addressProperty }
    val tag = bind(true) { headerFrom.tagProperty }
    val port = bind(true){headerFrom.portProperty}
}
