package pl.edu.pwr.weka.sipprogram.gui.model.header

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.AlgorithmEnum
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.AuthSchemeEnum
import pl.edu.pwr.weka.sipprogram.util.Generators
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 13.11.2018 22:35
 */

class HeaderWWWAuthenticate {
    val schemeProperty = SimpleObjectProperty<AuthSchemeEnum>(AuthSchemeEnum.DIGEST)
    var scheme by schemeProperty

    val algorithmProperty = SimpleObjectProperty<AlgorithmEnum>(AlgorithmEnum.MD5)
    var algorithm by algorithmProperty

    val realmProperty = SimpleStringProperty("")
    var realm by realmProperty

    val nonceProperty = SimpleStringProperty(Generators.calculateNonce())
    var nonce by nonceProperty

}

class HeaderWWWAuthenticateModel(val headerWWWAuthenticate: HeaderWWWAuthenticate): ViewModel() {
    constructor(): this(HeaderWWWAuthenticate())
    val scheme = bind(true){headerWWWAuthenticate.schemeProperty}
    val algorithm = bind(true){headerWWWAuthenticate.algorithmProperty}
    val realm = bind(true){headerWWWAuthenticate.realmProperty}
    val nonce = bind(true){headerWWWAuthenticate.nonceProperty}
}