package pl.edu.pwr.weka.sipprogram.gui.view.animation

import javafx.scene.layout.Priority
import javafx.scene.text.FontWeight
import kfoenix.jfxtextarea
import pl.edu.pwr.weka.sipprogram.gui.controller.animation.AnimationBasicController
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 27.11.2018 21:21
 */
class AnimationBasicFragment : Fragment() {

    val controller: AnimationBasicController by inject(this.scope, params)

    override val root = borderpane {
        vboxConstraints {
            vgrow = Priority.ALWAYS
        }
        center {
            val mainScene = AnimationViewObject.requestMainScene()
            mainScene.center {
                vbox vBoxContainer@{
                    minWidth = 100.0
                    controller.flowLines = children
                    controller.controlCenter(0)
                }
            }
            add(mainScene)
        }
        right {
            vbox {
                style {
                    padding = box(10.px)
                }
                prefWidth = 350.0
                maxWidth = 500.0
                spacing = 10.0
                label {
                    style {
                        fontWeight = FontWeight.EXTRA_BOLD
                        fontSize = 2.em
                    }
                    textProperty().bind(controller.model.descriptionTitle)
                }
                val computeWidth = this@vbox.widthProperty()
                        .subtract(this@vbox.paddingLeftProperty)
                        .subtract(this@vbox.paddingRightProperty)
                jfxtextarea {
                    prefWidthProperty().bind(computeWidth)
                    maxWidthProperty().bind(computeWidth)
                    isWrapText = true
                    isEditable = false
                    textProperty().bind(controller.model.description)
                    vboxConstraints {
                        vgrow = Priority.SOMETIMES
                    }
                }
                hbox {
                    var actualRequest = 0
                    spacing = 10.0
                    button("Wstecz") {
                        action {
                            actualRequest = controller.controlCenter(actualRequest - 1)
                        }
                    }
                    button("Naprzód") {
                        action {
                            actualRequest = controller.controlCenter(actualRequest + 1)
                        }
                    }
                    button("Czyść") {
                        action {
                            actualRequest = 0
                            controller.controlCenter(actualRequest)
                            controller.resetCenter()
                        }
                    }
                }
            }
        }
    }

    init {
        controller.resetCenter()
    }
}