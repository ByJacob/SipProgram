package pl.edu.pwr.weka.sipprogram.gui.model.header

import javafx.beans.property.SimpleIntegerProperty
import tornadofx.*

class HeaderMaxForwards {
    val maxForwardsProperty = SimpleIntegerProperty()
    var maxForwards by maxForwardsProperty

}

class HeaderMaxForwardsModel(headerMaxForwards: HeaderMaxForwards) : ViewModel() {
    constructor() : this(HeaderMaxForwards())

    val maxForwards = bind(true) { headerMaxForwards.maxForwardsProperty }
}
