package pl.edu.pwr.weka.sipprogram.gui2.view.fragment

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import javafx.beans.property.Property
import javafx.beans.property.SimpleIntegerProperty
import javafx.collections.FXCollections
import javafx.util.converter.NumberStringConverter
import kfoenix.jfxcheckbox
import kfoenix.jfxcombobox
import kfoenix.jfxtextarea
import kfoenix.jfxtextfield
import pl.edu.pwr.weka.sipprogram.gui2.model.FormBaseRequestModel
import pl.edu.pwr.weka.sipprogram.sip.auth.enums.AlgorithmEnum
import pl.edu.pwr.weka.sipprogram.sip.auth.enums.AuthMethodEnum
import pl.edu.pwr.weka.sipprogram.sip.auth.enums.QualityOfProtectionEnum
import pl.edu.pwr.weka.sipprogram.sip.request.base.RequestEnum
import tornadofx.*
import java.net.InetAddress

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 27.10.2018 16:13
 */
class FormRequestFragment : Fragment("FormRequestFragment") {

    val formRequestModel: FormBaseRequestModel by inject()

    override val root = Form()
    val requestComboBoxItems = FXCollections.observableArrayList(RequestEnum.values().toList())
    val authMethodComboBoxItems = FXCollections.observableArrayList(AuthMethodEnum.values().toList())
    val algorithmComboBoxItems = FXCollections.observableArrayList(AlgorithmEnum.values().toList())
    val qualityOfProtectionComboBoxItems = FXCollections.observableArrayList(QualityOfProtectionEnum.values().toList())

    init {
        title = "Wysyłany pakiet"

        with(root) {
            fieldset("Podstawowe dane", FontAwesomeIconView(FontAwesomeIcon.INFO)) {
                field("Typ pakietu") {
                    jfxcombobox<RequestEnum> {
                        items = requestComboBoxItems
                        bind(formRequestModel.request)
                    }
                }
                simpleField("Adres lokalny", InetAddress.getLocalHost().hostAddress, formRequestModel.localAddress)
                simpleField("Port lokalny", "8080", formRequestModel.localPort)
                simpleField("Adres serwera", "192.168.1.108", formRequestModel.serverAddress)
                simpleField("Port Serwera", "5160", formRequestModel.serverPort)
                simpleField("Użytkownik", "111", formRequestModel.user)
                simpleField("Hasło", "111", formRequestModel.password)
                simpleField("call-ID", "abcdef0123456789@1.1.1.1", formRequestModel.callId)
                simpleField("numer sekwencyjny", "1", formRequestModel.seqNumber)
            }
            fieldset("Autoryzacja") {
                jfxcheckbox("Włączona") {
                    bind(formRequestModel.authorizationEnabled)
                }
                field("Typ uwierzytelnienia") {
                    jfxcombobox<AuthMethodEnum> {
                        items = authMethodComboBoxItems
                        bind(formRequestModel.typeAuthorization)
                    }
                }
                simpleField("realm", "asterisk", formRequestModel.realmName)
                field("algorithm") {
                    jfxcombobox<AlgorithmEnum> {
                        items = algorithmComboBoxItems
                        bind(formRequestModel.algorithm)
                    }
                }
                simpleField("nonce", "1234567890abcdef", formRequestModel.nonce)
                field("qop") {
                    jfxcombobox<QualityOfProtectionEnum> {
                        items = qualityOfProtectionComboBoxItems
                        bind(formRequestModel.qop)
                    }
                }
            }
            fieldset("Wysyłany pakiet") {
                jfxtextarea {
                    bind(formRequestModel.requestTxtString)
                }
            }
        }
    }

    private fun simpleField(name: String, pt: String, b: Property<*>) {
        field(name) {
            jfxtextfield {
                isLabelFloat = true
                promptText = pt
                @Suppress("UNCHECKED_CAST")
                when(b.value){
                    is String -> bind(b as Property<String>)
                    is Number -> bind(b as Property<Number>, converter = NumberStringConverter())
                    else -> bind(b as Property<Any>)
                }

            }
        }
    }
}
