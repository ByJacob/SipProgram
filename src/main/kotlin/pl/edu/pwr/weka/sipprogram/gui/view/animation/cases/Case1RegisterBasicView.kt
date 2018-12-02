package pl.edu.pwr.weka.sipprogram.gui.view.animation.cases

import javafx.scene.layout.Priority
import javafx.scene.text.FontWeight
import kfoenix.jfxtextarea
import pl.edu.pwr.weka.sipprogram.gui.controller.animation.Case1RegisterBasicController
import pl.edu.pwr.weka.sipprogram.gui.view.animation.base.AnimationViewObject
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 27.11.2018 21:21
 */
class Case1RegisterBasicView : View() {

    val controller: Case1RegisterBasicController by inject(Scope())

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
                }
            }
            add(mainScene)
        }
        right {
            vbox {
                style {
                    padding = box(10.px)
                }
                prefWidth = 250.0
                spacing = 10.0
                label {
                    style {
                        fontWeight = FontWeight.EXTRA_BOLD
                        fontSize = 2.em
                    }
                    textProperty().bind(controller.model.descriptionTitle)
                }
                jfxtextarea {
                    isEditable = false
                    vboxConstraints {
                        vgrow = Priority.SOMETIMES
                    }
                    textProperty().bind(controller.model.description)
                }
                hbox {
                    var actualRequest = 0
                    val maxRequest = 4
                    spacing = 10.0
                    button("Wstecz") {
                        action {
                            actualRequest = if (actualRequest > 0) actualRequest - 1 else 0
                            controller.controlCenter(actualRequest)
                        }
                    }
                    button("Naprzód") {
                        action {
                            actualRequest = if (actualRequest < maxRequest) actualRequest + 1 else maxRequest
                            controller.controlCenter(actualRequest)
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