package pl.edu.pwr.weka.sipprogram.gui.model.header

import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class HeaderUserAgent {
    val userAgentProperty = SimpleStringProperty()
    var userAgent by userAgentProperty
}

class HeaderUserAgentModel(headerUserAgent: HeaderUserAgent) : ViewModel() {
    constructor() : this(HeaderUserAgent())

    val userAgent = bind(true) { headerUserAgent.userAgentProperty }
}
