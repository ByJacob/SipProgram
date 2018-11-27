package pl.edu.pwr.weka.sipprogram.gui.view.animation.base

import javafx.geometry.Pos
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.shape.Line
import javafx.util.Duration
import pl.edu.pwr.weka.sipprogram.gui.view.AnimationSipView
import pl.edu.pwr.weka.sipprogram.util.animation.SvgIcons
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 22.11.2018 22:14
 */
class AnimationViewObject : View() {
    override val root = vbox {}

    companion object {
        fun requestNode(requestName: String, isArrowRight: Boolean): VBox {
            return AnimationViewObject().requestNode(requestName, isArrowRight)
        }
        fun requestMainScene(): BorderPane {
            return AnimationViewObject().mainAnim
        }
    }

    val requestNode = { requestName: String, isArrowRight: Boolean ->
        val animationDuration = Duration.seconds(3.0)
        val arrowLineFixWidth = 25.0
        vbox requestVBox@{
            alignment = Pos.CENTER
            label(requestName)
            vbox {
                alignment = Pos.CENTER
                hbox {
                    alignment = if (isArrowRight) Pos.CENTER_LEFT else Pos.CENTER_RIGHT
                    if (!isArrowRight)
                        svgicon(SvgIcons.arrowLeft, 20)
                    line {
                        startX = 0.0
                        startY = 0.0
                        endY = 0.0
                        endX = 0.0
                        runLater(100.millis) {
                            timeline {
                                keyframe(animationDuration) {
                                    setOnFinished {
                                        endX = this@requestVBox.widthProperty().value - arrowLineFixWidth
                                        this@requestVBox.widthProperty().addListener { _, _, newValue ->
                                            runAsync {
                                                newValue.toDouble() - arrowLineFixWidth
                                            } ui { ex ->
                                                endX = ex
                                            }
                                        }
                                    }
                                    keyvalue(endXProperty(),
                                            this@requestVBox.widthProperty().value - arrowLineFixWidth)
                                }
                            }
                        }

                        strokeWidth = 3.0
                        strokeDashArray.addAll(30.0, 5.0)
                    }
                    if (isArrowRight)
                        svgicon(SvgIcons.arrowRight, 20)
                }
            }
        }
    }

    val mainAnim = borderpane {
        minHeight = 100.0
        style {
            padding = box(10.px)
        }
        left {
            vbox {
                alignment = Pos.TOP_CENTER
                spacing = 10.0
                label("Terminal")
                svgicon(SvgIcons.oldPhone, 40, Color.BLUE)
                line {
                    configureLineVertical(this,  this@left)
                }
            }
        }
        right {
            vbox {
                alignment = Pos.TOP_CENTER
                spacing = 10.0
                label("Serwer")
                svgicon(SvgIcons.server, 40, Color.BLUE)
                line {
                    configureLineVertical(this, this@right)
                }
            }
        }
    }

    private fun configureLineVertical(line: Line, borderPane: BorderPane) {
        line.startX = 0.0
        line.startY = 0.0
        line.endX = 0.0
        line.endYProperty().bind(borderPane.heightProperty().subtract(120))
        line.strokeWidth = 3.0
    }
}