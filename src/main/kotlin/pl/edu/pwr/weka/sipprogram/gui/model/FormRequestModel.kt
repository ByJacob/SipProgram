package pl.edu.pwr.weka.sipprogram.gui.model

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleObjectProperty
import pl.edu.pwr.weka.sipprogram.gui.fx.FormRequestFx
import pl.edu.pwr.weka.sipprogram.sip.request.Request
import pl.edu.pwr.weka.sipprogram.sip.request.base.RequestEnum

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 11.09.2018 18:45
 */

class FormRequestModel {
    val formRequestFxObjectProperty = SimpleObjectProperty<FormRequestFx>(FormRequestFx())
    val isRequestForm = SimpleBooleanProperty(true)
    val request = Request()

    init {
        RequestEnum.values().forEach { formRequestFxObjectProperty.get().requestListProperty.add(it) }
        initialValue()
        initialListeners()

    }

    private fun initialListeners() {
        formRequestFxObjectProperty.get().requestProperty
                .addListener { _, _, newValue -> request.method = newValue; updateTextArea() }
        formRequestFxObjectProperty.get().localAddressProperty
                .addListener { _, _, newValue -> request.localIpAddress = newValue; updateTextArea() }
        formRequestFxObjectProperty.get().localPortProperty
                .addListener { _, _, newValue -> request.localPort = newValue.toInt(); updateTextArea() }
        formRequestFxObjectProperty.get().serverAddressProperty
                .addListener { _, _, newValue -> request.serwerAddress = newValue; updateTextArea() }
        formRequestFxObjectProperty.get().serverPortProperty
                .addListener { _, _, newValue -> request.serwerPort = newValue.toInt(); updateTextArea() }
        formRequestFxObjectProperty.get().userProperty
                .addListener { _, _, newValue -> request.userLogin = newValue; updateTextArea() }
        formRequestFxObjectProperty.get().callIdProperty
                .addListener { _, _, newValue -> request.callId = newValue; updateTextArea() }
        formRequestFxObjectProperty.get().seqNumberProperty
                .addListener { _, _, newValue -> request.sequenceNumber = newValue.toLong(); updateTextArea() }
    }

    private fun updateTextArea() {
        formRequestFxObjectProperty.get().requestTxtStringProperty.set(request.prepareRequest().toString())
    }

    private fun initialValue() {
        formRequestFxObjectProperty.get().requestProperty.set(request.method)
        formRequestFxObjectProperty.get().localAddressProperty.set(request.localIpAddress)
        formRequestFxObjectProperty.get().localPortProperty.set(request.localPort.toString())
        formRequestFxObjectProperty.get().serverAddressProperty.set(request.serwerAddress)
        formRequestFxObjectProperty.get().serverPortProperty.set(request.serwerPort.toString())
        formRequestFxObjectProperty.get().userProperty.set(request.userLogin)
        formRequestFxObjectProperty.get().callIdProperty.set(request.callId)
        formRequestFxObjectProperty.get().seqNumberProperty.set(request.sequenceNumber.toString())
        updateTextArea()
    }
}