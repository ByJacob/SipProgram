package pl.edu.pwr.weka.sipprogram.gui.model.header

import gov.nist.javax.sip.Utils
import javafx.beans.property.SimpleStringProperty
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import tornadofx.*

class HeaderServer {
    val serverProperty = SimpleStringProperty("")
    var server by serverProperty

}

class HeaderServerModel(headerServer: HeaderServer): ViewModel() {
    constructor(): this(HeaderServer())
    val server = bind(true) { headerServer.serverProperty }
}
