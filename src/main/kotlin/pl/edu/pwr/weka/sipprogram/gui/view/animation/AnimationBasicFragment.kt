package pl.edu.pwr.weka.sipprogram.gui.view.animation

import javafx.beans.property.DoubleProperty
import javafx.beans.property.DoublePropertyBase
import javafx.scene.layout.Priority
import javafx.scene.text.FontPosture
import javafx.scene.text.FontWeight
import pl.edu.pwr.weka.sipprogram.Styles
import pl.edu.pwr.weka.sipprogram.gui.controller.animation.AnimationBasicController
import tornadofx.*
import java.util.function.DoublePredicate

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
        var widthProperty = widthProperty()
        center {
            val mainScene = AnimationViewObject.requestMainScene()
            mainScene.center {
                widthProperty = widthProperty()
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
                minWidth = 300.0
                widthProperty.addListener { _, _, newValue ->
                    runAsync { newValue } ui { prefWidth = newValue.toDouble() * 0.45}
                }
                maxWidth = 700.0
                spacing = 10.0
                label {
                    style {
                        fontWeight = FontWeight.EXTRA_BOLD
                        fontSize = 2.em
                    }
                    textProperty().bind(controller.model.descriptionTitle)
                }
                textflow {
                    addClass(Styles.materialTextArea)
                    vboxConstraints {
                        vgrow = Priority.SOMETIMES
                    }
                    text {
                        addClass(Stylesheet.text)
                        bind(controller.model.description, true)
                    }
                    vbox {
                        visibleWhen(controller.model.example.isNotBlank())
                        //managedWhen(controller.model.example.isNotBlank())
                        text {
                            addClass(Stylesheet.text)
                            text = "\n\n"
                        }
                        text {
                            addClass(Stylesheet.text)
                            text = messages["example_frame"]
                        }
                        text {
                            addClass(Stylesheet.text)
                            text = "\n"
                        }
                        text {
                            addClass(Stylesheet.text)
                            style {
                                fontStyle = FontPosture.ITALIC
                            }
                            bind(controller.model.example, true)
                        }
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