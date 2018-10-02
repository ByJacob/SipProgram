package pl.edu.pwr.weka.sipprogram.gui.controller

import com.jfoenix.controls.*
import io.datafx.controller.ViewController
import io.datafx.controller.ViewNode
import javafx.scene.layout.VBox
import pl.edu.pwr.weka.sipprogram.gui.model.FormRequestModel
import pl.edu.pwr.weka.sipprogram.sip.request.base.RequestEnum
import pl.edu.pwr.weka.sipprogram.util.CustomAnimations
import tornadofx.*
import java.util.*
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
    lateinit var authorizationVBox: VBox

    @ViewNode
    lateinit var realmJFXTextField: JFXTextField

    @ViewNode
    lateinit var uriJFXTextField: JFXTextField

    @ViewNode
    lateinit var nonceJFXTextField: JFXTextField

    @ViewNode
    lateinit var opaqueJFXTextField: JFXTextField

    @ViewNode
    lateinit var ncJFXTextField: JFXTextField

    @ViewNode
    lateinit var cnonceJFXTextField: JFXTextField

    @ViewNode
    lateinit var qopJFXChipView: JFXChipView<String>

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
                .bindBidirectional(formRequestModel.formRequestFxObjectProperty.get().requestListProperty)
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

        passwordJFXTextField.disableProperty().bind(authorizationJFXCheckBox.selectedProperty())
    }

    private fun createListeners() {
        formRequestModel.formRequestFxObjectProperty.get().requestProperty.addListener { _, _, _ -> updateFormFields() }
        authorizationJFXCheckBox.selectedProperty().addListener { _, _, newValue ->
            CustomAnimations.setVisibleUndManagedAnimated(newValue, authorizationVBox)
        }
    }

    private fun setDefaultProperties() {
        requestJFXComboBox.selectionModel.select(RequestEnum.REGISTER)
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
