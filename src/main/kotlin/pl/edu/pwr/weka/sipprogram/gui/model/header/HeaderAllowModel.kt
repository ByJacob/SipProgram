package pl.edu.pwr.weka.sipprogram.gui.model.header

import javafx.beans.property.SimpleListProperty
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.RequestEnum
import tornadofx.*

class HeaderAllow {

    val allowListProperty = SimpleListProperty<RequestEnum>()
    var allowList by allowListProperty

}

class HeaderAllowModel(val headerAllow: HeaderAllow) : ViewModel() {
    constructor() : this(HeaderAllow())

    val allowList = bind(true){headerAllow.allowListProperty}
}
