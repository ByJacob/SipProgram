package pl.edu.pwr.weka.sipprogram.gui.model.header

import javafx.beans.property.SimpleListProperty
import javafx.collections.FXCollections
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.RequestEnum
import tornadofx.*

class HeaderAllow {

}

class HeaderAllowModel(val headerAllow: HeaderAllow) : ViewModel() {
    constructor() : this(HeaderAllow())

    val allowList = SimpleListProperty<RequestEnum>(FXCollections.observableArrayList())
}
