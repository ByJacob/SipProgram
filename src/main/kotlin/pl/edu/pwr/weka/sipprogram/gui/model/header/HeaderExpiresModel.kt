package pl.edu.pwr.weka.sipprogram.gui.model.header

import javafx.beans.property.SimpleIntegerProperty
import tornadofx.*

class HeaderExpires {
    val expiresdProperty = SimpleIntegerProperty(0)
    var expiresd by expiresdProperty

}

class HeaderExpiresModel(headerExpires: HeaderExpires) : ViewModel() {
    constructor(): this(HeaderExpires())
    val expires = bind(true) { headerExpires.expiresdProperty }
}
