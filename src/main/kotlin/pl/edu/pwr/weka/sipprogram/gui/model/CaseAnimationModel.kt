package pl.edu.pwr.weka.sipprogram.gui.model

import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import tornadofx.getValue
import tornadofx.setValue

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 28.11.2018 11:54
 */
class CaseAnimation(){

    val descriptionTitleProperty = SimpleStringProperty()
    var descriptionTitle by descriptionTitleProperty


    val descriptionProperty = SimpleStringProperty()
    var description by descriptionProperty

}

class CaseAnimationModel : ItemViewModel<CaseAnimation>() {
    val descriptionTitle = bind(CaseAnimation::descriptionTitleProperty, true)
    val description = bind(CaseAnimation::descriptionProperty, true)
}
