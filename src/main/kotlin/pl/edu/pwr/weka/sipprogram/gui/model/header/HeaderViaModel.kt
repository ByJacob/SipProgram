package pl.edu.pwr.weka.sipprogram.gui.model.header

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.TransportProtocol
import tornadofx.*
import kotlin.random.Random

class HeaderVia {
    val protocolProperty = SimpleObjectProperty<TransportProtocol>(TransportProtocol.UDP)
    var protocol by protocolProperty

    val addressProperty = SimpleStringProperty(SipProtocol.ip)
    var address by addressProperty

    val portProperty = SimpleIntegerProperty(SipProtocol.port)
    var port by portProperty

    val branchProperty = SimpleStringProperty(
            "z9hG4bK" + Random.nextLong(0, Long.MAX_VALUE).toString(16))
    var branch by branchProperty

}

class HeaderViaModel(val headerVia: HeaderVia) : ViewModel() {
    constructor() : this(HeaderVia())

    val protocol = bind(true) { headerVia.protocolProperty }
    val address = bind(true) { headerVia.addressProperty }
    val port = bind(true) { headerVia.portProperty }
    val branch = bind(true) { headerVia.branchProperty }
}
