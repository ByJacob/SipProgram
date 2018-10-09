package pl.edu.pwr.weka.sipprogram.gui.model

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleObjectProperty
import pl.edu.pwr.weka.sipprogram.gui.fx.FormRequestFx
import pl.edu.pwr.weka.sipprogram.sip.auth.enums.AlgorithmEnum
import pl.edu.pwr.weka.sipprogram.sip.auth.enums.AuthMethodEnum
import pl.edu.pwr.weka.sipprogram.sip.auth.enums.QualityOfProtectionEnum
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
        AuthMethodEnum.values().forEach { formRequestFxObjectProperty.get().typeAuthorizationListProperty.add(it) }
        QualityOfProtectionEnum.values().forEach { formRequestFxObjectProperty.get().qopListProperty.add(it) }
        AlgorithmEnum.values().forEach { formRequestFxObjectProperty.get().algorithmPropertyList.add(it) }
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
                .addListener { _, _, newValue -> request.serverAddress = newValue; updateTextArea() }
        formRequestFxObjectProperty.get().serverPortProperty
                .addListener { _, _, newValue -> request.serverPort = newValue.toInt(); updateTextArea() }
        formRequestFxObjectProperty.get().userProperty
                .addListener { _, _, newValue -> request.userLogin = newValue; updateTextArea() }
        formRequestFxObjectProperty.get().passwordProperty
                .addListener { _, _, newValue -> request.userPassword = newValue; updateTextArea() }
        formRequestFxObjectProperty.get().callIdProperty
                .addListener { _, _, newValue -> request.callId = newValue; updateTextArea() }
        formRequestFxObjectProperty.get().seqNumberProperty
                .addListener { _, _, newValue -> request.sequenceNumber = newValue.toLong(); updateTextArea() }
        //Auth
        formRequestFxObjectProperty.get().authorizationEnabledProperty
                .addListener { _, _, newValue -> request.authorization.enabled = newValue; updateTextArea() }
        formRequestFxObjectProperty.get().typeAuthorizationProperty
                .addListener { _, _, newValue -> request.authorization.type = newValue; updateTextArea() }
        formRequestFxObjectProperty.get().realmNameProperty
                .addListener { _, _, newValue -> request.authorization.realm = newValue; updateTextArea() }
        formRequestFxObjectProperty.get().algorithmProperty
                .addListener { _, _, newValue -> request.authorization.algorithm = newValue; updateTextArea() }
        formRequestFxObjectProperty.get().nonceProperty
                .addListener { _, _, newValue -> request.authorization.nonce = newValue; updateTextArea() }
        formRequestFxObjectProperty.get().qopsProperty
                .addListener { _, _, newValue -> request.authorization.qops = newValue; updateTextArea() }
    }

    private fun updateTextArea() {
        formRequestFxObjectProperty.get().requestTxtStringProperty.set(request.prepareRequest().toString())
    }

    private fun initialValue() {
        formRequestFxObjectProperty.get().requestProperty.set(request.method)
        formRequestFxObjectProperty.get().localAddressProperty.set(request.localIpAddress)
        formRequestFxObjectProperty.get().localPortProperty.set(request.localPort.toString())
        formRequestFxObjectProperty.get().serverAddressProperty.set(request.serverAddress)
        formRequestFxObjectProperty.get().serverPortProperty.set(request.serverPort.toString())
        formRequestFxObjectProperty.get().userProperty.set(request.userLogin)
        formRequestFxObjectProperty.get().callIdProperty.set(request.callId)
        formRequestFxObjectProperty.get().seqNumberProperty.set(request.sequenceNumber.toString())
        //AUth
        formRequestFxObjectProperty.get().authorizationEnabledProperty.set(request.authorization.enabled)
        formRequestFxObjectProperty.get().typeAuthorizationProperty.set(request.authorization.type)
        formRequestFxObjectProperty.get().realmNameProperty.set(request.authorization.realm)
        formRequestFxObjectProperty.get().algorithmProperty.set(request.authorization.algorithm)
        formRequestFxObjectProperty.get().nonceProperty.set(request.authorization.nonce)
        formRequestFxObjectProperty.get().qopsProperty.setAll(request.authorization.qops)

        updateTextArea()
    }
}