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

class AnimationCanvas {
    val nameProperty = SimpleStringProperty("")
    var name by nameProperty

    val descriptionProperty = SimpleStringProperty("")
    var description by descriptionProperty

    val titleProperty = SimpleStringProperty("")
    var title by titleProperty

    val arrowExampleProperty = SimpleStringProperty("")
    var arrowExample by arrowExampleProperty

    val documentationUrlProperty = SimpleStringProperty("")
    var documentationUrl by documentationUrlProperty


}

class AnimationCanvasModel : ItemViewModel<AnimationCanvas>(AnimationCanvas()) {
    val name = bind(AnimationCanvas::nameProperty, true)
    val description = bind(AnimationCanvas::descriptionProperty, true)
    val title = bind(AnimationCanvas::titleProperty, true)
    val arrowExample = bind(AnimationCanvas::arrowExampleProperty, true)
    val documentationUrl = bind(AnimationCanvas::documentationUrlProperty, true)
}
