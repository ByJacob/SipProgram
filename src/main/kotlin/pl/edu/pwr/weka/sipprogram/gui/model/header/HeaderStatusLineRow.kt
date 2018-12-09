package pl.edu.pwr.weka.sipprogram.gui.model.header

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.RequestEnum
import tornadofx.*

class HeaderStatusLineRow {
    val methodProperty = SimpleObjectProperty<RequestEnum>(RequestEnum.ACK)
    var method by methodProperty

    val statusCodeProperty = SimpleIntegerProperty(0)
    var statusCode by statusCodeProperty


    val messageProperty = SimpleStringProperty("")
    var message by messageProperty


}

class HeaderStatusLineModel(val headerStatusLineRow: HeaderStatusLineRow) : ViewModel() {
    constructor(): this(HeaderStatusLineRow())
    val method = bind(true){headerStatusLineRow.methodProperty}
    val statusCode = bind(true){headerStatusLineRow.statusCodeProperty}
    val message = bind(true){headerStatusLineRow.messageProperty}
}

