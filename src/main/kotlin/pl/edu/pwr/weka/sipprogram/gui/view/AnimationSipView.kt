package pl.edu.pwr.weka.sipprogram.gui.view

import javafx.collections.FXCollections
import javafx.scene.layout.Priority
import javafx.util.Duration
import kfoenix.jfxlistview
import pl.edu.pwr.weka.sipprogram.gui.view.animation.AnimationBasicFragment
import pl.edu.pwr.weka.sipprogram.gui.view.animation.AnimationCaseEnum
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 20.11.2018 23:47
 */
class AnimationSipView : View("Animation") {


    override val root = borderpane {
        left {
            vbox {
                style {
                    padding = box(10.px)
                }
                jfxlistview<AnimationCaseEnum> {
                    items = FXCollections.observableArrayList(AnimationCaseEnum.values().toList())
                    onUserSelect(clickCount = 1) {
                        fire(ChangeAnimationEvent(it.parameters))
                    }
                    runLater {
                        selectionModel.select(0)
                        fire(ChangeAnimationEvent(AnimationCaseEnum.values()[0].parameters))
                    }
                    vboxConstraints {
                       vGrow = Priority.ALWAYS
                    }
                }
            }
        }
        center {
            vbox {
                subscribe<ChangeAnimationEvent> { context ->
                    this@vbox.replaceChildren(find<AnimationBasicFragment>("parameters" to context.parameters))
                }
                vgrow = Priority.ALWAYS
            }
        }

    }

    class ChangeAnimationEvent(val parameters: List<AnimationCaseEnum.AnimationCaseEnumParameters>) : FXEvent()
}
