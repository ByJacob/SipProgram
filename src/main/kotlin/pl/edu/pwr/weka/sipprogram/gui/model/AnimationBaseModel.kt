package pl.edu.pwr.weka.sipprogram.gui.model

import javafx.beans.property.SimpleStringProperty
import tornadofx.*

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

    val exampleProperty = SimpleStringProperty()
    var example by exampleProperty

    val urlProperty = SimpleStringProperty()
    var url by urlProperty


}

class AnimationBaseModel : ItemViewModel<CaseAnimation>() {
    val descriptionTitle = bind(CaseAnimation::descriptionTitleProperty, true)
    val description = bind(CaseAnimation::descriptionProperty, true)
    val example = bind(CaseAnimation::exampleProperty, true)
    val url = bind(CaseAnimation::urlProperty)
}
