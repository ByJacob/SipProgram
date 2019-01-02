package pl.edu.pwr.weka.sipprogram.gui.view.fragment

import javafx.beans.binding.Bindings
import javafx.scene.control.ScrollPane
import javafx.scene.layout.Priority
import javafx.scene.text.FontPosture
import javafx.scene.text.FontWeight
import pl.edu.pwr.weka.sipprogram.Styles
import pl.edu.pwr.weka.sipprogram.gui.controller.AnimationCanvasController
import pl.edu.pwr.weka.sipprogram.gui.view.animation.AnimationCanvasArrowsView
import pl.edu.pwr.weka.sipprogram.gui.view.animation.AnimationCanvasBackgroundView
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 19.12.2018 23:46
 */
class AnimationCanvasFragment : Fragment() {

    val controller: AnimationCanvasController by inject(Scope(), params)

    override val root = borderpane {
        vboxConstraints {
            vGrow = Priority.ALWAYS
        }
        center {
            vbox {
                scrollpane {
                    isFitToWidth = true
                    stackpane {
                        style {
                            padding = box(10.px)
                        }
                        this.prefHeightProperty().onChange {
                            this@scrollpane.vvalue = this@scrollpane.vmax
                        }
                        val background = AnimationCanvasBackgroundView(controller.scenario.endpoints)
                        background.widthProperty().bind(this.widthProperty())
                        background.heightProperty().bind(this.heightProperty())
                        add(background)
                        val arrow = AnimationCanvasArrowsView(
                            background.sixthLayerLineY,
                            controller.scenario.endpoints.size
                        )
                        arrow.widthProperty().bind(this.widthProperty())
                        arrow.heightProperty().bind(this.heightProperty())
                        val vBoxHeight = this@vbox.heightProperty().subtract(20)
                        val arrowsHeight = arrow.prefHeightProperty
                        this.prefHeightProperty().bind(
                            Bindings.`when`(vBoxHeight.greaterThan(arrowsHeight))
                                .then(vBoxHeight)
                                .otherwise(arrowsHeight)
                        )
                        add(arrow)
                        subscribe<NextArrowEvent> {
                            arrow.drawArrowAnimation(it.arrow)
                        }
                        subscribe<ClearArrowEvent> {
                            arrow.clearCanvas()
                        }
                    }
                    vboxConstraints {
                        vGrow = Priority.ALWAYS
                    }
                }
            }
        }
        right {
            vbox textContainer@{
                maxWidth = 400.0
                prefWidth = maxWidth
                style {
                    padding = box(10.px)
                }
                spacing = 10.0
                label {
                    style {
                        fontWeight = FontWeight.EXTRA_BOLD
                        fontSize = 2.em
                    }
                    textProperty().bind(controller.model.title)
                    isWrapText = true
                }
                scrollpane {
                    addClass(Styles.scrollBarAnimationDescription)
                    style {
                        padding = box(5.px)
                    }
                    vboxConstraints {
                        vgrow = Priority.ALWAYS
                    }
                    vbox {
                        addClass(Styles.containerDAnimationDescription)
                        text {
                            addClass(Stylesheet.text)
                            bind(controller.model.description, true)
                            wrappingWidthProperty().bind(this@textContainer.widthProperty().subtract(35))
                        }
                        vbox {
                            visibleWhen(controller.model.arrowExample.isNotBlank())
                            managedWhen(controller.model.arrowExample.isNotBlank())
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
                                bind(controller.model.arrowExample, true)
                            }
                        }
                    }
                }
                vbox {
                    prefWidthProperty().bind(
                        this@textContainer.widthProperty()
                            .subtract(this@textContainer.paddingLeftProperty)
                            .subtract(this@textContainer.paddingRightProperty)
                    )
                    hyperlink(messages["hyperlink_rfc"]) {
                        visibleWhen { controller.model.documentationUrl.isNotBlank() }
                        action {
                            if (controller.model.documentationUrl.value.isNotEmpty())
                                hostServices.showDocument(controller.model.documentationUrl.value)
                        }
                    }
                }
                hbox {
                    spacing = 10.0
                    button("Wstecz") {
                        action {
                            controller.controlAnimation(false)
                        }
                        shortcut("LEFT")
                    }
                    button("Naprzód") {
                        action {
                            controller.controlAnimation(true)
                        }
                        shortcut("RIGHT")
                    }
                    button("Czyść") {
                        action {
                            controller.resetAnimation()
                        }
                    }
                }
            }
        }
    }

    class NextArrowEvent(val arrow: AnimationCanvasArrowsView.ArrowProperties) : FXEvent()
    class ClearArrowEvent : FXEvent()

    data class ScenariosProperties(
        val name: String,
        val description: String = "",
        val documentationUrl: String = "",
        val endpoints: List<AnimationCanvasBackgroundView.EndPointProperties>,
        val arrows: List<AnimationCanvasArrowsView.ArrowProperties>
    ) {
        override fun toString(): String {
            return name
        }
    }
}