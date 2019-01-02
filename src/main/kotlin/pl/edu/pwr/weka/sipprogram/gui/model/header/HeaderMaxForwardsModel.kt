package pl.edu.pwr.weka.sipprogram.gui.model.header

import javafx.beans.property.SimpleIntegerProperty
import tornadofx.*

class HeaderMaxForwards {
    val maxForwardsProperty = SimpleIntegerProperty(0)
    var maxForwards by maxForwardsProperty

}

class HeaderMaxForwardsModel(val headerMaxForwards: HeaderMaxForwards) : ViewModel() {
    constructor() : this(HeaderMaxForwards())

    val maxForwards = bind(true) { headerMaxForwards.maxForwardsProperty }
}
