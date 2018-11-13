package pl.edu.pwr.weka.sipprogram.gui.model.header

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.RequestEnum
import tornadofx.*

class HeaderRequestLineRow {
    val methodProperty = SimpleObjectProperty<RequestEnum>(RequestEnum.REGISTER)
    var method by methodProperty

    val requestHostProperty = SimpleStringProperty("")
    var requestHost by requestHostProperty

    val requestPortProperty = SimpleIntegerProperty(0)
    var requestPort by requestPortProperty

}

class HeaderRequestLineModel(val headerRequestLineRow: HeaderRequestLineRow) : ViewModel() {
    constructor(): this(HeaderRequestLineRow())
    val method = bind(true){headerRequestLineRow.methodProperty}
    val requestHost = bind(true){headerRequestLineRow.requestHostProperty}
    val requestPort = bind(true){headerRequestLineRow.requestPortProperty}
}

