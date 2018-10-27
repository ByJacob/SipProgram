package pl.edu.pwr.weka.sipprogram.gui2.model

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import pl.edu.pwr.weka.sipprogram.sip.auth.enums.AlgorithmEnum
import pl.edu.pwr.weka.sipprogram.sip.auth.enums.AuthMethodEnum
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 27.10.2018 16:25
 */
class FormAuthRequestFx(authorizationEnabled: Boolean,
                        typeAuthorization: AuthMethodEnum,
                        realmName: String,
                        algorithm: AlgorithmEnum,
                        nonce: String,
                        qop: String) {
    val authorizationEnabledProperty = SimpleBooleanProperty(authorizationEnabled)
    var authorizationEnabled by authorizationEnabledProperty

    val typeAuthorizationProperty = SimpleObjectProperty<AuthMethodEnum>(typeAuthorization)
    var typeAuthorization by typeAuthorizationProperty

    val realmNameProperty = SimpleStringProperty(realmName)
    var realmName by realmNameProperty

    val algorithmProperty = SimpleObjectProperty<AlgorithmEnum>(algorithm)
    var algorithm by algorithmProperty

    val nonceProperty = SimpleStringProperty(nonce)
    var nonce by nonceProperty

    val qopProperty = SimpleStringProperty(qop)
    var qop by qopProperty

}

class FormAuthRequestFxModel : ItemViewModel<FormAuthRequestFx>() {
    val authorizationEnabled = bind(FormAuthRequestFx::authorizationEnabledProperty)
    val typeAuthorization = bind(FormAuthRequestFx::typeAuthorizationProperty)
    val realmName = bind(FormAuthRequestFx::realmNameProperty)
    val algorithm = bind(FormAuthRequestFx::algorithmProperty)
    val nonce = bind(FormAuthRequestFx::nonceProperty)
    val qop = bind(FormAuthRequestFx::qopProperty)
}
