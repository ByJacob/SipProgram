package pl.edu.pwr.weka.sipprogram.gui.model

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleObjectProperty
import pl.edu.pwr.weka.sipprogram.sip.request.base.RequestEnum
import tornadofx.*
import tornadofx.getValue
import tornadofx.setValue

class FormRequest {

    val methodProperty = SimpleObjectProperty<RequestEnum>(RequestEnum.REGISTER)
    var method by methodProperty

    val isSendingRequestProperty = SimpleBooleanProperty(true)
    var isSendingRequest by isSendingRequestProperty

}

class FormRequestModel(val formRequest: FormRequest): ViewModel() {
    constructor(): this(FormRequest())
    val method = bind(true) {formRequest.methodProperty}
    val isSendingRequest = bind(true) {formRequest.isSendingRequestProperty}
}
