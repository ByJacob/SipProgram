package pl.edu.pwr.weka.sipprogram.gui.model

import javafx.beans.property.SimpleObjectProperty
import pl.edu.pwr.weka.sipprogram.gui.view.fragment.FormRequestFragment
import tornadofx.*

class ProcessConnection {
    val formRequestFragmentProperty = SimpleObjectProperty<FormRequestFragment>()
    var formRequestFragment by formRequestFragmentProperty

}

class ProcessConnectionModel(val processConnection: ProcessConnection) : ViewModel() {
    constructor(): this(ProcessConnection())

    val formRequestFragment = bind(true) { processConnection.formRequestFragmentProperty }
}
