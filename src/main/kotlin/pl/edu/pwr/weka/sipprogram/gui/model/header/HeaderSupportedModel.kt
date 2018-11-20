package pl.edu.pwr.weka.sipprogram.gui.model.header

import javafx.beans.property.SimpleListProperty
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.SupportedEnum
import tornadofx.*

class HeaderSupported {

    val supportedListProperty = SimpleListProperty<SupportedEnum>()
    var supportedList by supportedListProperty

}

class HeaderSupportedModel(val headerSupported: HeaderSupported) : ViewModel() {
    constructor() : this(HeaderSupported())

    val supportedList = bind(true) { headerSupported.supportedListProperty }
}
