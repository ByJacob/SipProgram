package pl.edu.pwr.weka.sipprogram.gui.view

import javafx.beans.property.SimpleDoubleProperty
import javafx.collections.FXCollections
import javafx.scene.layout.Priority
import kfoenix.jfxlistview
import pl.edu.pwr.weka.sipprogram.gui.view.animation.cases.Case1RegisterBasic
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 20.11.2018 23:47
 */
class AnimationSipView : View("Animation") {

    var centerTopPadding = SimpleDoubleProperty(0.0)


    override val root = borderpane {
        left {
            jfxlistview<AnimationCaseEnum> {
                items = FXCollections.observableArrayList(AnimationCaseEnum.values().toList())
                onUserSelect(clickCount = 1) {
                    fire(ChangeAnimationEvent(it.animClass))
                }
            }
        }
        center {
            vbox {
                add(Case1RegisterBasic::class)
                subscribe<ChangeAnimationEvent> {
                    clear()
                    val view = it.animClass.newInstance() as View
                    add(view)
                }
                vgrow = Priority.ALWAYS
            }
        }

    }

    class ChangeAnimationEvent(val animClass: Class<*>): FXEvent()
}

enum class AnimationCaseEnum(val animName: String, val animClass: Class<*>){
    BASIC_REGISTER("Basic Register", Case1RegisterBasic::class.java);

    override fun toString(): String{
        return animName
    }


}
