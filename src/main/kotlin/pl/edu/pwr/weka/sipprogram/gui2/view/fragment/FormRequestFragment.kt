package pl.edu.pwr.weka.sipprogram.gui2.view.fragment

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import javafx.beans.property.Property
import javafx.beans.property.SimpleIntegerProperty
import javafx.collections.FXCollections
import javafx.scene.control.Label
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
                field("Adres lokalny") {
                    jfxtextfield {
                        promptText = InetAddress.getLocalHost().hostAddress
                        bind(formRequestModel.localAddress)

                    }
                }
                field("Port lokalny") {
                    jfxtextfield {
                        promptText = "8080"
                        bind(formRequestModel.localPort)

                    }
                }
                field("Adres serwera") {
                    jfxtextfield {
                        promptText = "192.168.1.108"
                        bind(formRequestModel.serverAddress)

                    }
                }
                field("Port Serwera") {
                    jfxtextfield {
                        promptText = "5160"
                        bind(formRequestModel.serverPort)

                    }
                }
                field("Użytkownik") {
                    jfxtextfield {
                        promptText = "111"
                        bind(formRequestModel.user)

                    }
                }
                field("Hasło") {
                    jfxtextfield {
                        promptText = "111"
                        bind(formRequestModel.password)

                    }
                }
                field("Call-ID") {
                    jfxtextfield {
                        promptText = "abcdef0123456789@1.1.1.1"
                        bind(formRequestModel.callId)

                    }
                }
                field("Numer Sekwencyjny") {
                    jfxtextfield {
                        promptText = "1"
                        bind(formRequestModel.seqNumber)

                    }
                }
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
                field("realm") {
                    jfxtextfield {
                        promptText = "asterisk"
                        bind(formRequestModel.realmName)

                    }
                }
                field("algorithm") {
                    jfxcombobox<AlgorithmEnum> {
                        items = algorithmComboBoxItems
                        bind(formRequestModel.algorithm)
                    }
                }
                field("nonce") {
                    jfxtextfield {
                        promptText = "1234567890abcdef"
                        bind(formRequestModel.nonce)

                    }
                }
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
}
