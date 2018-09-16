package pl.edu.pwr.weka.sipprogram.gui.controller

import com.jfoenix.controls.JFXComboBox
import com.jfoenix.controls.JFXTextArea
import com.jfoenix.controls.JFXTextField
import io.datafx.controller.ViewController
import io.datafx.controller.ViewNode
import pl.edu.pwr.weka.sipprogram.gui.model.FormRequestModel
import pl.edu.pwr.weka.sipprogram.sip.request.base.RequestEnum
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
    lateinit var callIdJFXTextField: JFXTextField
    @ViewNode
    lateinit var sequenceNumberJFXTextField: JFXTextField
    @ViewNode
    lateinit var textAreaJFXTextArea: JFXTextArea

    val formRequestModel = FormRequestModel()

    @PostConstruct
    fun init() {
        requestJFXComboBox.itemsProperty()
                .bindBidirectional(formRequestModel.formRequestFxObjectProperty.get().requestListProperty)
        formRequestModel.formRequestFxObjectProperty.get().requestProperty
                .bind(requestJFXComboBox.selectionModel.selectedItemProperty())
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
        formRequestModel.formRequestFxObjectProperty.addListener { t -> println(t.toString()) }
    }

}
