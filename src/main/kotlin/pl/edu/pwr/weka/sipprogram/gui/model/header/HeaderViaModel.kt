package pl.edu.pwr.weka.sipprogram.gui.model.header

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.TransportProtocol
import tornadofx.*

class HeaderVia {
    val protocolProperty = SimpleObjectProperty<TransportProtocol>(TransportProtocol.UDP)
    var protocol by protocolProperty

    val addressProperty = SimpleStringProperty("")
    var address by addressProperty

    val portProperty = SimpleIntegerProperty(0)
    var port by portProperty

    val branchProperty = SimpleStringProperty("")
    var branch by branchProperty

}

class HeaderViaModel(headerVia: HeaderVia) : ViewModel() {
    constructor() : this(HeaderVia())

    val protocol = bind(true) { headerVia.protocolProperty }
    val address = bind(true) { headerVia.addressProperty }
    val port = bind(true) { headerVia.portProperty }
    val branch = bind(true) { headerVia.branchProperty }
}
