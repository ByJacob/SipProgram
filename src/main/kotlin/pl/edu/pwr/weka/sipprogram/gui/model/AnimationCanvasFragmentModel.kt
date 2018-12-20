package pl.edu.pwr.weka.sipprogram.gui.model

import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import tornadofx.getValue
import tornadofx.setValue

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 19.12.2018 22:22
 */

class AnimationFragmentCanvas {
    val nameProperty = SimpleStringProperty("")
    var name by nameProperty

    val descriptionProperty = SimpleStringProperty("")
    var description by descriptionProperty

    val arrowTitleProperty = SimpleStringProperty("")
    var arrowTitle by arrowTitleProperty

    val arrowDescriptionProperty = SimpleStringProperty("")
    var arrowDescription by arrowDescriptionProperty

    val arrowExampleProperty = SimpleStringProperty("")
    var arrowExample by arrowExampleProperty

    val documentationUrlProperty = SimpleStringProperty("")
    var documentationUrl by documentationUrlProperty


}

class AnimationCanvasFragmentModel : ItemViewModel<AnimationFragmentCanvas>() {
    val name = bind(AnimationFragmentCanvas::nameProperty, true)
    val description = bind(AnimationFragmentCanvas::descriptionProperty, true)
    val arrowTitle = bind(AnimationFragmentCanvas::arrowTitleProperty, true)
    val arrowDescription = bind(AnimationFragmentCanvas::arrowDescriptionProperty, true)
    val arrowExample = bind(AnimationFragmentCanvas::arrowExampleProperty, true)
    val documentationUrl = bind(AnimationFragmentCanvas::documentationUrlProperty, true)
}
