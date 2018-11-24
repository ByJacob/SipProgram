package pl.edu.pwr.weka.sipprogram.gui.model.header

import gov.nist.javax.sip.Utils
import javafx.beans.property.SimpleStringProperty
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import tornadofx.*

class HeaderCallId {
    val callIdProperty = SimpleStringProperty(Utils.getInstance().generateCallIdentifier(SipProtocol.ip))
    var callId by callIdProperty

}

class HeaderCallIdModel(val headerCallId: HeaderCallId): ViewModel() {
    constructor(): this(HeaderCallId())
    val callId = bind(true) { headerCallId.callIdProperty }
}
