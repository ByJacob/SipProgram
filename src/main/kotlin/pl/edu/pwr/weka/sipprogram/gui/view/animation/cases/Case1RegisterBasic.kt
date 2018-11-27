package pl.edu.pwr.weka.sipprogram.gui.view.animation.cases

import javafx.scene.layout.Priority
import javafx.scene.text.FontWeight
import kfoenix.jfxtextarea
import pl.edu.pwr.weka.sipprogram.gui.view.animation.base.AnimationViewObject
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 27.11.2018 21:21
 */
class Case1RegisterBasic : View() {

    val textRequest0 = "Bez rejestracji można się komunikować jedynie bezpośrednio do innego użytkownika, ale i w takim przypadku należy znać adres IP użytkownika docelowego. Proces rejestracji usuwa te problemy, i bez przeszkód można się komunikować z innym użytkownikiem.\nREGISTER kojarzy identyfikator użytkownika – AOR (Address of Record) z jedną lub większą ilością lokalizacji. AOR można zarejestrować na wielu urządzeniach. Podczas rejestracji wysyłany jest nagłówek Contact w którym zawarte jest AOR i adres IP.  Nagłówek ten informuje gdzie chcemy otrzymywać żądania."

    val textRequest1 = "Użytkownik wysyła zapytanie REGISTER do rejestratora SIP. Nagłówki From i To powinny zawierać takie samo AOR użytkownika. Użytkownik dodatkowo może określić czas, przez który rejestracja powinna być ważna za pomocą nagłówka Expires. Wartość ta może być później zmodyfikowana przez rejestrator."

    val textRequest2 = "Rejestrator zwraca wiadomość z kodem 401 Unauthorized. W wiadomości tej znajduje się nagłówek WWW-Authenticate zawierający dane, które muszą zostać użyte do zaszyfrowania hasła użytkownika. Najważniejszymi parametrami z tego nagłówka jest unikalny numer (nonce) i algorytm szyfrowania."

    val textRequest3 = "Użytkownik wysyła zapytanie REGISTER do rejestratora SIP. Zapytanie to zawiera nagłówek Authorization. W tym nagłówku znajduje się zaszyfrowane hasło użytkownika."

    val textRequest4 = "Gdy hasło jest poprawne rejestrator zwraca wiadomość z kodem 200 OK, aby potwierdzić poprawną rejestracje. Zwracany jest także nagłówek Expires, mówiący o tym przez jaki czas rejestracja będzie ważna."

    override val root = borderpane {
        vboxConstraints {
            vgrow = Priority.ALWAYS
        }
        center {
            val mainScene = AnimationViewObject.requestMainScene()
            mainScene.center {
                vbox vBoxContainer@{
                    minWidth = 200.0
                    subscribe<Case1Clear> {
                        clear()
                        vbox {
                            prefHeight = 120.0
                            maxHeight = 120.0
                            minHeight = 120.0
                        }
                    }
                    subscribe<Case1Request1> {
                        if (this@vBoxContainer.children.size < 2)
                            add(AnimationViewObject.requestNode("REGISTER", true))
                    }

                    subscribe<Case1Request2> {
                        if (this@vBoxContainer.children.size < 3)
                            add(AnimationViewObject.requestNode("401 Unauthorized", false))
                    }

                    subscribe<Case1Request3> {
                        if (this@vBoxContainer.children.size < 4)
                            add(AnimationViewObject.requestNode("REGISTER", true))
                    }

                    subscribe<Case1Request4> {
                        if (this@vBoxContainer.children.size < 5)
                            add(AnimationViewObject.requestNode("200 OK", false))
                    }
                }
            }
            add(mainScene)
        }
        right {
            vbox {
                style {
                    padding = box(10.px)
                }
                prefWidth = 350.0
                spacing = 10.0
                label {
                    style {
                        fontWeight = FontWeight.EXTRA_BOLD
                        fontSize = 2.em
                    }
                    subscribe<Case1Request0> {
                        text = "START"
                    }
                    subscribe<Case1Request1> {
                        text = "REGISTER - 1"
                    }

                    subscribe<Case1Request2> {
                        text = "401 Unauthorized"
                    }

                    subscribe<Case1Request3> {
                        text = "Register - 2"
                    }

                    subscribe<Case1Request4> {
                        text = "200 OK"
                    }
                }
                jfxtextarea {
                    isEditable = false
                    vboxConstraints {
                        vgrow = Priority.SOMETIMES
                    }
                    subscribe<Case1Request0> {
                        text = textRequest0
                    }
                    subscribe<Case1Request1> {
                        text = textRequest1
                    }

                    subscribe<Case1Request2> {
                        text = textRequest2
                    }

                    subscribe<Case1Request3> {
                        text = textRequest3
                    }

                    subscribe<Case1Request4> {
                        text = textRequest4
                    }
                }
                hbox {
                    var actualRequest = 0
                    val maxRequest = 4
                    spacing = 10.0
                    button("Wstecz") {
                        action {
                            actualRequest = if (actualRequest > 0) actualRequest - 1 else 0
                            fire(Case1Button())
                        }
                    }
                    button("Naprzód") {
                        action {
                            actualRequest = if (actualRequest < maxRequest) actualRequest + 1 else maxRequest
                            fire(Case1Button())
                        }
                    }
                    button("Czyść") {
                        action {
                            actualRequest = 0
                            fire(Case1Button())
                            fire(Case1Clear())
                        }
                    }
                    subscribe<Case1Button> {
                        when (actualRequest) {
                            0 -> fire(Case1Request0())
                            1 -> fire(Case1Request1())
                            2 -> fire(Case1Request2())
                            3 -> fire(Case1Request3())
                            4 -> fire(Case1Request4())
                        }
                    }
                }
            }
        }
    }

    init {
        runLater { fire(Case1Button()) }
        runLater { fire(Case1Clear()) }
    }

    class Case1Button : FXEvent()
    class Case1Clear : FXEvent()
    class Case1Request0 : FXEvent()
    class Case1Request1 : FXEvent()
    class Case1Request2 : FXEvent()
    class Case1Request3 : FXEvent()
    class Case1Request4 : FXEvent()
}