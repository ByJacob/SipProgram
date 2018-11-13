package pl.edu.pwr.weka.sipprogram.gui.model.header

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.RequestEnum
import tornadofx.*

class HeaderCSeq(){
    val numberProperty = SimpleIntegerProperty(0)
    var number by numberProperty

    val methodProperty = SimpleObjectProperty<RequestEnum>(RequestEnum.REGISTER)
    var method by methodProperty

}

class HeaderCSeqModel(headerCSeq: HeaderCSeq): ViewModel() {
    constructor(): this(HeaderCSeq())
    val number = bind(true){headerCSeq.numberProperty}
    val method = bind(true){headerCSeq.methodProperty}
}
