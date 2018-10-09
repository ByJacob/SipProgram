package pl.edu.pwr.weka.sipprogram.gui.controller

import com.jfoenix.controls.*
import io.datafx.controller.ViewController
import io.datafx.controller.ViewNode
import javafx.collections.ListChangeListener
import javafx.scene.layout.VBox
import pl.edu.pwr.weka.sipprogram.gui.model.FormRequestModel
import pl.edu.pwr.weka.sipprogram.sip.auth.enums.AlgorithmEnum
import pl.edu.pwr.weka.sipprogram.sip.auth.enums.AuthMethodEnum
import pl.edu.pwr.weka.sipprogram.sip.auth.enums.QualityOfProtectionEnum
import pl.edu.pwr.weka.sipprogram.sip.request.base.RequestEnum
import pl.edu.pwr.weka.sipprogram.util.CustomAnimations
import javax.annotation.PostConstruct


/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 11.09.2018 18:40
 */
@ViewController("/fxml/FormRequest.fxml")
class FormRequestController {

    @ViewNode
    lateinit var requestJFXComboBox: JFXComboBox<RequestEnum>

    @ViewNode
    lateinit var localAddressJFXTextField: JFXTextField

    @ViewNode
    lateinit var localPortJFXTextField: JFXTextField

    @ViewNode
    lateinit var serverAddressJFXTextField: JFXTextField

    @ViewNode
    lateinit var serverPortJFXTextField: JFXTextField

    @ViewNode
    lateinit var userJFXTextField: JFXTextField

    @ViewNode
    lateinit var passwordJFXTextField: JFXTextField

    @ViewNode
    lateinit var authorizationMainVBox: VBox

    @ViewNode
    lateinit var authorizationJFXCheckBox: JFXCheckBox

    @ViewNode
    lateinit var typeAuthorizationJFXComboBox: JFXComboBox<AuthMethodEnum>

    @ViewNode
    lateinit var authorizationVBox: VBox

    @ViewNode
    lateinit var realmJFXTextField: JFXTextField

    @ViewNode
    lateinit var algorithmJFXComboBox: JFXComboBox<AlgorithmEnum>

    @ViewNode
    lateinit var nonceJFXTextField: JFXTextField

    @ViewNode
    lateinit var qopJFXChipView: JFXChipView<QualityOfProtectionEnum>

    @ViewNode
    lateinit var callIdJFXTextField: JFXTextField

    @ViewNode
    lateinit var sequenceNumberJFXTextField: JFXTextField

    @ViewNode
    lateinit var textAreaJFXTextArea: JFXTextArea

    val formRequestModel = FormRequestModel()

    @PostConstruct
    fun init() {
        setDefaultProperties()
        bindingAllFields()
        createListeners()
        updateFormFields()
    }

    private fun bindingAllFields() {
        formRequestModel.formRequestFxObjectProperty.get().requestProperty
                .bind(requestJFXComboBox.selectionModel.selectedItemProperty())
        textAreaJFXTextArea.textProperty()
                .bind(formRequestModel.formRequestFxObjectProperty.get().requestTxtStringProperty)
        requestJFXComboBox.itemsProperty()
                .bind(formRequestModel.formRequestFxObjectProperty.get().requestListProperty)
        localAddressJFXTextField.textProperty()
                .bindBidirectional(formRequestModel.formRequestFxObjectProperty.get().localAddressProperty)
        localPortJFXTextField.textProperty()
                .bindBidirectional(formRequestModel.formRequestFxObjectProperty.get().localPortProperty)
        serverAddressJFXTextField.textProperty()
                .bindBidirectional(formRequestModel.formRequestFxObjectProperty.get().serverAddressProperty)
        serverPortJFXTextField.textProperty()
                .bindBidirectional(formRequestModel.formRequestFxObjectProperty.get().serverPortProperty)
        userJFXTextField.textProperty()
                .bindBidirectional(formRequestModel.formRequestFxObjectProperty.get().userProperty)
        callIdJFXTextField.textProperty()
                .bindBidirectional(formRequestModel.formRequestFxObjectProperty.get().callIdProperty)
        sequenceNumberJFXTextField.textProperty()
                .bindBidirectional(formRequestModel.formRequestFxObjectProperty.get().seqNumberProperty)
        //Auth
        passwordJFXTextField.disableProperty().bind(authorizationJFXCheckBox.selectedProperty().not())
        passwordJFXTextField.textProperty()
                .bindBidirectional(formRequestModel.formRequestFxObjectProperty.get().passwordProperty)
        formRequestModel.formRequestFxObjectProperty.get().authorizationEnabledProperty
                .bind(authorizationJFXCheckBox.selectedProperty())
        formRequestModel.formRequestFxObjectProperty.get().typeAuthorizationProperty
                .bind(typeAuthorizationJFXComboBox.selectionModel.selectedItemProperty())
        typeAuthorizationJFXComboBox.itemsProperty()
                .bind(formRequestModel.formRequestFxObjectProperty.get().typeAuthorizationListProperty)
        realmJFXTextField.textProperty()
                .bindBidirectional(formRequestModel.formRequestFxObjectProperty.get().realmNameProperty)
        formRequestModel.formRequestFxObjectProperty.get().algorithmProperty
                .bind(algorithmJFXComboBox.selectionModel.selectedItemProperty())
        algorithmJFXComboBox.itemsProperty()
                .bindBidirectional(formRequestModel.formRequestFxObjectProperty.get().algorithmPropertyList)
        nonceJFXTextField.textProperty()
                .bindBidirectional(formRequestModel.formRequestFxObjectProperty.get().nonceProperty)
        qopJFXChipView.suggestions.addAll(formRequestModel.formRequestFxObjectProperty.get().qopListProperty)
        qopJFXChipView.chips.addListener { c: ListChangeListener.Change<out QualityOfProtectionEnum> ->
            if (c.next()) {
                try {
                    for (qualityOfProtectionEnum in c.addedSubList) {
                        val count = c.list.count { it == qualityOfProtectionEnum }
                        if (count > 1)
                            c.list.remove(c.from, c.to)
                    }
                    formRequestModel.formRequestFxObjectProperty.get().qopsProperty.setAll(c.list)
                } catch (e: ClassCastException) {
                    c.list.remove(c.from, c.to)
                }
            }
        }
    }

    private fun createListeners() {
        formRequestModel.formRequestFxObjectProperty.get().requestProperty.addListener { _, _, _ -> updateFormFields() }
        authorizationJFXCheckBox.selectedProperty().addListener { _, _, newValue ->
            CustomAnimations.setVisibleUndManagedAnimated(newValue, authorizationVBox)
        }
    }

    private fun setDefaultProperties() {
        requestJFXComboBox.selectionModel.select(RequestEnum.REGISTER)
        typeAuthorizationJFXComboBox.selectionModel.select(AuthMethodEnum.DIGEST)
        algorithmJFXComboBox.selectionModel.select(AlgorithmEnum.MD5)
        authorizationJFXCheckBox.selectedProperty().value = false
        authorizationVBox.managedProperty().value = false
        authorizationVBox.visibleProperty().value = false
    }

    private fun updateFormFields() {
        val requestEnum = formRequestModel.formRequestFxObjectProperty.get().requestProperty.get()
        if (requestEnum != null) {
            when (requestEnum) {
                RequestEnum.REGISTER -> {
                }
                RequestEnum.INVITE -> {
                }
            }
        }
    }


}
