package pl.edu.pwr.weka.sipprogram.gui.view

import javafx.beans.property.SimpleDoubleProperty
import javafx.geometry.Pos
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Region
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.shape.Line
import javafx.util.Duration
import pl.edu.pwr.weka.sipprogram.util.animation.SvgIcons
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 20.11.2018 23:47
 */
class AnimationSipView : View("Animation") {

    var centerTopPadding = SimpleDoubleProperty(0.0)
    val animationDuration = Duration.seconds(3.0)
    val arrowLineFixWidth = 25.0

    override val root = borderpane {
        prefWidth = 800.0
        prefHeight = 600.0
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
                    configureLineVertical(this, this@vbox, this@left)
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
                    configureLineVertical(this, this@vbox, this@right)
                }
            }
        }
        center {
            vbox {
                vbox {
                    prefHeightProperty().bind(centerTopPadding)
                    maxHeightProperty().bind(centerTopPadding)
                    minHeightProperty().bind(centerTopPadding)
                }
                vbox requestVBox@{
                    alignment = Pos.CENTER
                    subscribe<Scene1Request1> {
                        label("REGISTER")
                        vbox {
                            alignment = Pos.CENTER
                            hbox {
                                alignment = Pos.CENTER_LEFT
                                line {
                                    startX = 0.0
                                    startY = 0.0
                                    endY = 0.0
                                    endX = 0.0
                                    timeline {
                                        keyframe(animationDuration) {
                                            setOnFinished {
                                                this@requestVBox.widthProperty().addListener { _, _, newValue ->
                                                    runAsync {
                                                        newValue.toDouble() - arrowLineFixWidth
                                                    } ui {
                                                        endX = it
                                                    }
                                                }
                                            }
                                            keyvalue(endXProperty(), this@requestVBox.widthProperty().value - arrowLineFixWidth)
                                        }
                                    }

                                    strokeWidth = 3.0
                                    strokeDashArray.addAll(30.0, 5.0)
                                }
                                svgicon(SvgIcons.arrowRight, 20)
                            }
                        }
                    }
                }
                vbox requestVBox@{
                    alignment = Pos.CENTER
                    subscribe<Scene1Request2> {
                        label("401 Unauthorized")
                        vbox {
                            alignment = Pos.CENTER
                            hbox {
                                alignment = Pos.CENTER_RIGHT
                                svgicon(SvgIcons.arrowLeft, 20)
                                line {
                                    startX = 0.0
                                    startY = 0.0
                                    endY = 0.0
                                    timeline {
                                        keyframe(animationDuration) {
                                            setOnFinished {
                                                this@requestVBox.widthProperty().addListener { _, _, newValue ->
                                                    runAsync {
                                                        newValue.toDouble() - arrowLineFixWidth
                                                    } ui {
                                                        endX = it
                                                    }
                                                }
                                            }
                                            keyvalue(endXProperty(), this@requestVBox.widthProperty().value - arrowLineFixWidth)
                                        }

                                    }
                                    //endXProperty().bind(lineWidthProperty.subtract(30))
                                    strokeWidth = 3.0
                                    strokeDashArray.addAll(30.0, 5.0)
                                }
                            }
                        }
                    }
                }
                vbox requestVBox@{
                    alignment = Pos.CENTER
                    subscribe<Scene1Request3> {
                        label("REGISTER")
                        vbox {
                            alignment = Pos.CENTER
                            hbox {
                                alignment = Pos.CENTER_LEFT
                                line {
                                    startX = 0.0
                                    startY = 0.0
                                    endY = 0.0
                                    timeline {
                                        keyframe(animationDuration) {
                                            setOnFinished {
                                                this@requestVBox.widthProperty().addListener { _, _, newValue ->
                                                    runAsync {
                                                        newValue.toDouble() - arrowLineFixWidth
                                                    } ui {
                                                        endX = it
                                                    }
                                                }
                                            }
                                            keyvalue(endXProperty(), this@requestVBox.widthProperty().value - arrowLineFixWidth)
                                        }

                                    }
                                    //endXProperty().bind(lineWidthProperty.subtract(30))
                                    strokeWidth = 3.0
                                    strokeDashArray.addAll(30.0, 5.0)
                                }
                                svgicon(SvgIcons.arrowRight, 20)
                            }
                        }
                    }
                }
                vbox requestVBox@{
                    alignment = Pos.CENTER
                    subscribe<Scene1Request4> {
                        label("200 OK")
                        vbox {
                            alignment = Pos.CENTER
                            hbox {
                                alignment = Pos.CENTER_RIGHT
                                svgicon(SvgIcons.arrowLeft, 20)
                                line {
                                    startX = 0.0
                                    startY = 0.0
                                    endY = 0.0
                                    timeline {
                                        keyframe(animationDuration) {
                                            setOnFinished {
                                                this@requestVBox.widthProperty().addListener { _, _, newValue ->
                                                    runAsync {
                                                        newValue.toDouble() - arrowLineFixWidth
                                                    } ui {
                                                        endX = it
                                                    }
                                                }
                                            }
                                            keyvalue(endXProperty(), this@requestVBox.widthProperty().value - arrowLineFixWidth)
                                        }
                                    }
                                    //endXProperty().bind(lineWidthProperty.subtract(30))
                                    strokeWidth = 3.0
                                    strokeDashArray.addAll(30.0, 5.0)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    init {
        runLater(1.seconds) { fire(Scene1Request1()) }
        runLater(6.seconds) { fire(Scene1Request2()) }
        runLater(11.seconds) { fire(Scene1Request3()) }
        runLater(16.seconds) { fire(Scene1Request4()) }
    }

    private fun configureLineVertical(line: Line, vBox: VBox, borderPane: BorderPane) {
        line.startX = 0.0
        line.startY = 0.0
        line.endX = 0.0
        var heightProperty = SimpleDoubleProperty(0.0).add(0)
        vBox.getChildList()?.forEach {
            if (it !is Line && it is Region)
                heightProperty = heightProperty.add(it.heightProperty()).add(11)
        }
        heightProperty = heightProperty.add(21)
        centerTopPadding.bind(heightProperty.subtract(21))
        line.endYProperty().bind(borderPane.heightProperty().subtract(heightProperty))
        line.strokeWidth = 3.0
    }

    class Scene1Request1 : FXEvent()
    class Scene1Request2 : FXEvent()
    class Scene1Request3 : FXEvent()
    class Scene1Request4 : FXEvent()
}
