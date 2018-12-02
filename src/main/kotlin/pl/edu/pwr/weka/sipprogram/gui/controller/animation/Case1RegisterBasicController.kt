package pl.edu.pwr.weka.sipprogram.gui.controller.animation

import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.scene.Node
import javafx.scene.layout.VBox
import pl.edu.pwr.weka.sipprogram.gui.model.CaseAnimationModel
import pl.edu.pwr.weka.sipprogram.gui.view.animation.base.AnimationViewObject
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 28.11.2018 11:47
 */
class Case1RegisterBasicController : Controller() {

    val textRequest0 = "Bez rejestracji można się komunikować jedynie bezpośrednio do innego użytkownika, ale i w takim przypadku należy znać adres IP użytkownika docelowego. Proces rejestracji usuwa te problemy, i bez przeszkód można się komunikować z innym użytkownikiem.\nREGISTER kojarzy identyfikator użytkownika – AOR (Address of Record) z jedną lub większą ilością lokalizacji. AOR można zarejestrować na wielu urządzeniach. Podczas rejestracji wysyłany jest nagłówek Contact w którym zawarte jest AOR i adres IP.  Nagłówek ten informuje gdzie chcemy otrzymywać żądania."

    val textRequest1 = "Użytkownik wysyła zapytanie REGISTER do rejestratora SIP. Nagłówki From i To powinny zawierać takie samo AOR użytkownika. Użytkownik dodatkowo może określić czas, przez który rejestracja powinna być ważna za pomocą nagłówka Expires. Wartość ta może być później zmodyfikowana przez rejestrator."

    val textRequest2 = "Rejestrator zwraca wiadomość z kodem 401 Unauthorized. W wiadomości tej znajduje się nagłówek WWW-Authenticate zawierający dane, które muszą zostać użyte do zaszyfrowania hasła użytkownika. Najważniejszymi parametrami z tego nagłówka jest unikalny numer (nonce) i algorytm szyfrowania."

    val textRequest3 = "Użytkownik wysyła zapytanie REGISTER do rejestratora SIP. Zapytanie to zawiera nagłówek Authorization. W tym nagłówku znajduje się zaszyfrowane hasło użytkownika."

    val textRequest4 = "Gdy hasło jest poprawne rejestrator zwraca wiadomość z kodem 200 OK, aby potwierdzić poprawną rejestracje. Zwracany jest także nagłówek Expires, mówiący o tym przez jaki czas rejestracja będzie ważna."

    val model: CaseAnimationModel by inject()

    lateinit var flowLines: ObservableList<Node>
    val descriptionTitleProperty = SimpleStringProperty("")

    init {

        subscribe<Case1Request0> {
            model.descriptionTitle.value = "START"
            model.description.value = textRequest0
        }
        subscribe<Case1Request1> {
            if (flowLines.size < 2)
                flowLines.add(AnimationViewObject.requestNode("REGISTER", true))
            model.descriptionTitle.value = "REGISTER - 1"
            model.description.value = textRequest1
        }

        subscribe<Case1Request2> {
            if (flowLines.size < 3)
                flowLines.add(AnimationViewObject.requestNode("401 Unauthorized", false))
            model.descriptionTitle.value = "401 Unauthorized"
            model.description.value = textRequest2
        }

        subscribe<Case1Request3> {
            if (flowLines.size < 4)
                flowLines.add(AnimationViewObject.requestNode("REGISTER", true))
            model.descriptionTitle.value = "Register - 2"
            model.description.value = textRequest3
        }

        subscribe<Case1Request4> {
            if (flowLines.size < 5)
                flowLines.add(AnimationViewObject.requestNode("200 OK", false))
            model.descriptionTitle.value = "200 OK"
            model.description.value = textRequest4
        }
    }

    fun resetCenter() {
        flowLines.clear()
        val vBox = VBox()
        vBox.prefHeight = 120.0
        vBox.maxHeight = 120.0
        vBox.minHeight = 120.0
        flowLines.add(vBox)
    }

    fun controlCenter(actualRequest: Int) {
        when (actualRequest) {
            0 -> fire(Case1Request0())
            1 -> fire(Case1Request1())
            2 -> fire(Case1Request2())
            3 -> fire(Case1Request3())
            4 -> fire(Case1Request4())
        }
    }


    class Case1Request0 : FXEvent()
    class Case1Request1 : FXEvent()
    class Case1Request2 : FXEvent()
    class Case1Request3 : FXEvent()
    class Case1Request4 : FXEvent()
}