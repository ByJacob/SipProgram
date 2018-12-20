package pl.edu.pwr.weka.sipprogram.gui.view.animation

import javafx.animation.AnimationTimer
import javafx.animation.KeyFrame
import javafx.animation.KeyValue
import javafx.animation.Timeline
import javafx.beans.property.SimpleDoubleProperty
import javafx.event.EventHandler
import javafx.util.Duration
import pl.edu.pwr.weka.sipprogram.gui.view.AnimationCanvasView
import pl.edu.pwr.weka.sipprogram.util.CanvasResizable

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 18.12.2018 20:08
 */
class AnimationCanvasArrowsView(sixthLayerLineY: Double, val countEndPoints: Int) : CanvasResizable() {

    private var endPointsX = listOf<Double>()

    private val arrows = mutableListOf<ArrowProperties>()
    private val startArrowY = sixthLayerLineY + 10
    private val arrowHeight = 20.0
    private val spaceBetweenArrows = 20.0
    private val dartWidth = 15.0

    init {
        endPointsX = AnimationCanvasView.calculateEndPointsX(countEndPoints, width)
    }

    override fun draw() {
        endPointsX = AnimationCanvasView.calculateEndPointsX(countEndPoints, width)
        arrows.forEachIndexed { index, properties ->
            val fromX = endPointsX[properties.from]
            val toX = endPointsX[properties.to]
            drawArrow(
                fromX,
                toX,
                startArrowY + (index * (arrowHeight+spaceBetweenArrows)),
                properties.name
            )
        }
    }

    fun drawArrowAnimation(properties: ArrowProperties) {
        if (properties.from < 0
            && properties.from > endPointsX.size
            && properties.to < 0
            && properties.to > endPointsX.size
        ) {
            throw IndexOutOfBoundsException(
                "Bat properties about arrow. From:" +
                        "${properties.from}, To:${properties.to}, Endpoints last index: ${endPointsX.lastIndex}"
            )
        }
        val fromX = endPointsX[properties.from]
        val toX = endPointsX[properties.to]
        val x = SimpleDoubleProperty(fromX)
        when {
            fromX < toX -> {
                x.value += 5
            }
            fromX > toX -> {
                x.value -= 5
            }
        }
        val y = startArrowY + (arrows.size * (arrowHeight+spaceBetweenArrows))
        val timeline = Timeline(
            KeyFrame(Duration.seconds(3.0), KeyValue(x, toX))
        )
        val timer = object : AnimationTimer() {
            override fun handle(now: Long) {
                drawArrow(fromX, x.doubleValue(), y, properties.name)
            }
        }
        timeline.onFinished = EventHandler {
            timer.stop()
        }
        timer.start()
        timeline.play()
        arrows.add(properties)
    }

    private fun drawArrow(fromX: Double, toX: Double, y: Double, message: String) {
        var tFromX = fromX
        var tToX = toX
        var isRight = true
        if (tFromX < tToX) {
            tFromX += 5
            tToX -= 5
            gc.clearRect(tFromX - dartWidth - 2,
                y - 2,
                tToX + dartWidth + 2 - tFromX + 2,
                arrowHeight + 4)
            if (tFromX > tToX)
                tToX = tFromX
        } else {
            tFromX -= 5
            tToX += 5
            gc.clearRect(tToX - 2,
                y - 2,
                tFromX + dartWidth - tToX + 7,
                arrowHeight + 4)
            if (tFromX < tToX)
                tFromX = tToX
            isRight = false

        }
        val xPoints = mutableListOf(tFromX, tToX)
        val yPoints = mutableListOf(y + (arrowHeight / 2), y + (arrowHeight / 2))

        yPoints.addAll(listOf(y, y + (arrowHeight / 2), y + arrowHeight))
        if (isRight) {
            xPoints.addAll(listOf(tToX - dartWidth, tToX+2, tToX - dartWidth))
        } else {
            xPoints.addAll(listOf(tToX + dartWidth, tToX-2, tToX + dartWidth))
        }
        gc.strokePolyline(xPoints.toDoubleArray(), yPoints.toDoubleArray(), 5)
    }

    data class ArrowProperties(val from: Int,
                               val to: Int,
                               val name: String,
                               val title: String = "",
                               val description: String = "",
                               val example: String = "")
}