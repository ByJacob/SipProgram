package pl.edu.pwr.weka.sipprogram.gui.view

import com.jfoenix.controls.JFXListCell
import javafx.collections.FXCollections
import javafx.scene.layout.Priority
import javafx.util.Callback
import kfoenix.jfxlistview
import pl.edu.pwr.weka.sipprogram.gui.view.animation.AnimationBasicFragment
import pl.edu.pwr.weka.sipprogram.gui.view.animation.AnimationCaseEnum
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 20.11.2018 23:47
 */
class AnimationSipView : View(FX.messages["animation"]) {


    override val root = borderpane {
        left {
            vbox {
                style {
                    padding = box(10.px)
                }
                jfxlistview<AnimationCaseEnum> {
                    cellFactory = Callback { param ->
                        val jfxListCell = JFXListCell<AnimationCaseEnum>()
                        this@jfxlistview.prefWidthProperty().bind(jfxListCell.widthProperty().add(2))
//                        jfxListCell.prefWidthProperty().bind(this@jfxlistview.widthProperty().subtract(2))
//                        jfxListCell.maxWidth = Control.USE_PREF_SIZE
                        jfxListCell
                    }
                    items = FXCollections.observableArrayList(AnimationCaseEnum.values().toList())
                    onUserSelect(clickCount = 1) {
                        fire(ChangeAnimationEvent(it))
                    }
                    runLater {
                        selectionModel.select(0)
                        fire(ChangeAnimationEvent(AnimationCaseEnum.values()[0]))
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
                    this@vbox.replaceChildren(find<AnimationBasicFragment>("parameters" to context.case.parameters,
                            "documentationUrl" to context.case.documentationUrl))
                }
                vgrow = Priority.ALWAYS
            }
        }

    }

    class ChangeAnimationEvent(val case: AnimationCaseEnum) : FXEvent()
}
