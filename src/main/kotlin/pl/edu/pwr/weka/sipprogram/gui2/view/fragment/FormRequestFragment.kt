package pl.edu.pwr.weka.sipprogram.gui2.view.fragment

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import javafx.collections.FXCollections
import javafx.util.converter.NumberStringConverter
import kfoenix.jfxcheckbox
import kfoenix.jfxcombobox
import kfoenix.jfxtextarea
import kfoenix.jfxtextfield
import pl.edu.pwr.weka.sipprogram.gui2.model.FormRequestModel
import pl.edu.pwr.weka.sipprogram.gui2.model.mapper.mapToRequest
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

    val formRequestModel: FormRequestModel by inject()

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
                        maxWidth = Double.MAX_VALUE
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
                field("Adres serwera") {
                    jfxtextfield {
                        promptText = "192.168.1.108"
                        bind(formRequestModel.serverAddress)

                    }
                }
                field("Port Serwera") {
                    jfxtextfield {
                        promptText = "5160"
                        bind(formRequestModel.serverPort, converter = NumberStringConverter("###"))

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
                        bind(formRequestModel.seqNumber, converter = NumberStringConverter("###"))

                    }
                }
            }
            fieldset("Autoryzacja", FontAwesomeIconView(FontAwesomeIcon.LOCK)) {
                jfxcheckbox("Włączona") {
                    bind(formRequestModel.authorizationEnabled)
                }
                fieldset {
                    subscribe<EnableAuthorizationEvent> {
                        clear()
                        if (it.isEnabled) {
                            field("Typ uwierzytelnienia") {
                                jfxcombobox<AuthMethodEnum> {
                                    maxWidth = Double.MAX_VALUE
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
                                    maxWidth = Double.MAX_VALUE
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
                                    maxWidth = Double.MAX_VALUE
                                    items = qualityOfProtectionComboBoxItems
                                    bind(formRequestModel.qop)
                                }
                            }
                        }
                    }
                    runAsync {
                        fire(EnableAuthorizationEvent(formRequestModel.authorizationEnabled.value))
                    }
                }
            }
            fieldset("Wysyłany pakiet", FontAwesomeIconView(FontAwesomeIcon.FILE_TEXT)) {
                jfxtextarea {
                    minHeight = 250.0
                    isEditable = false
                    bind(formRequestModel.requestTxtString)
                    subscribe<FormRequestFragment.UpdateRequestTextAreaEvent> {
                        formRequestModel.formRequest.requestTxtSpring =
                                formRequestModel.formRequest.mapToRequest().prepareRequest().toString()
                    }
                }
            }
        }
        runAsync {
            fire(UpdateRequestTextAreaEvent())
        }
    }

    class EnableAuthorizationEvent(val isEnabled: Boolean) : FXEvent()

    class UpdateRequestTextAreaEvent: FXEvent()
}
