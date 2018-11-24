package pl.edu.pwr.weka.sipprogram.gui.view

import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.layout.VBox
import javafx.util.Duration
import pl.edu.pwr.weka.sipprogram.util.animation.SvgIcons
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 22.11.2018 22:14
 */
class AnimationViewObject: View() {
    override val root = vbox{}
    companion object {
        fun requestNode(requestName: String, isArrowRight: Boolean): VBox {
            return AnimationViewObject().requestNode(requestName, isArrowRight)
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
}