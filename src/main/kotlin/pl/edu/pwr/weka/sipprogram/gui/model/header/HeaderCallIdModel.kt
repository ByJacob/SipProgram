package pl.edu.pwr.weka.sipprogram.gui.model.header

import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class HeaderCallId {
    val callIdProperty = SimpleStringProperty("")
    var callId by callIdProperty

}

class HeaderCallIdModel(headerCallId: HeaderCallId): ViewModel() {
    constructor(): this(HeaderCallId())
    val callId = bind(true) { headerCallId.callIdProperty }
}
