package pl.edu.pwr.weka.sipprogram.gui.model

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.RequestEnum
import tornadofx.*

class FormRequest {

    val methodProperty = SimpleObjectProperty<RequestEnum>(RequestEnum.REGISTER)
    var method by methodProperty

    val statusCodeProperty = SimpleIntegerProperty(0)
    var statusCode by statusCodeProperty


    val messageStatusCodeProperty = SimpleStringProperty("")
    var messageStatusCode by messageStatusCodeProperty


    val isSendingRequestProperty = SimpleBooleanProperty(true)
    var isSendingRequest by isSendingRequestProperty

}

class FormRequestModel(val formRequest: FormRequest): ViewModel() {
    constructor(): this(FormRequest())
    val method = bind(true) {formRequest.methodProperty}
    val statusCode = bind(true){formRequest.statusCodeProperty}
    val messageStatusCode = bind(true){formRequest.messageStatusCodeProperty}
    val isSendingRequest = bind(true) {formRequest.isSendingRequestProperty}
}
