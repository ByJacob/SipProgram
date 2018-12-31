package pl.edu.pwr.weka.sipprogram.gui.model.header

import javafx.beans.property.SimpleListProperty
import javafx.collections.FXCollections
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.SupportedEnum
import tornadofx.*

class HeaderSupported

class HeaderSupportedModel(val headerSupported: HeaderSupported) : ViewModel() {
    constructor() : this(HeaderSupported())

    val supportedList = SimpleListProperty<SupportedEnum>(FXCollections.observableArrayList())
}
