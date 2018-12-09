package pl.edu.pwr.weka.sipprogram.gui.model.header

import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class HeaderServer {
    val serverProperty = SimpleStringProperty("")
    var server by serverProperty

}

class HeaderServerModel(val headerServer: HeaderServer): ViewModel() {
    constructor(): this(HeaderServer())
    val server = bind(true) { headerServer.serverProperty }
}
