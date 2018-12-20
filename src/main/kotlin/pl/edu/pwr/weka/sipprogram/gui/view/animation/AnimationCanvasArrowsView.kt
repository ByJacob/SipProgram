package pl.edu.pwr.weka.sipprogram.gui.view.animation

import javafx.animation.AnimationTimer
import javafx.animation.KeyFrame
import javafx.animation.KeyValue
import javafx.animation.Timeline
import javafx.beans.property.SimpleDoubleProperty
import javafx.event.EventHandler
import javafx.geometry.Point2D
import javafx.scene.text.Text
import javafx.util.Duration
import pl.edu.pwr.weka.sipprogram.gui.view.ScenariosView
import pl.edu.pwr.weka.sipprogram.util.CanvasResizable
import java.awt.Point
import kotlin.math.round
import kotlin.math.roundToInt

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 18.12.2018 20:08
 */

class AnimationCanvasArrowsView(sixthLayerLineY: Double, val countEndPoints: Int) : CanvasResizable() {

    private var endPointsX = listOf<Double>()

    private val arrows = mutableListOf<ArrowProperties>()
    private val animatedArrows = mutableListOf<ArrowAnimationProperties>()
    private val startArrowY = sixthLayerLineY + 10
    private val arrowHeight = 20.0
    private val spaceBetweenArrows = 20.0
    private val dartWidth = 15.0

    init {
        endPointsX = ScenariosView.calculateEndPointsX(countEndPoints, width)
    }

    override fun draw() {
        endPointsX = ScenariosView.calculateEndPointsX(countEndPoints, width)
        animatedArrows.forEach {
            it.timer.stop()
            it.timeline.stop()
        }
        val animatedArrowsList = animatedArrows.toList()
        animatedArrows.clear()
        animatedArrowsList.forEach {
            drawArrowAnimation(it.properties)
        }

        arrows.forEachIndexed { index, properties ->
            val fromX = endPointsX[properties.from]
            val toX = endPointsX[properties.to]
            drawArrow(
                fromX,
                toX,
                startArrowY + (index * (arrowHeight + spaceBetweenArrows)),
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
        val y = startArrowY + ((arrows.size + animatedArrows.size) * (arrowHeight + spaceBetweenArrows))
        val timeline = Timeline(
            KeyFrame(Duration.seconds(3.0), KeyValue(x, toX))
        )
        val timer = object : AnimationTimer() {
            override fun handle(now: Long) {
                drawArrow(fromX, x.doubleValue(), y, properties.name)
            }
        }
        val arrowAnimationProperties = ArrowAnimationProperties(properties, timeline, timer)
        timeline.onFinished = EventHandler {
            timer.stop()
            animatedArrows.remove(arrowAnimationProperties)
            arrows.add(properties)
        }
        timer.start()
        timeline.play()
        animatedArrows.add(arrowAnimationProperties)
    }

    fun clearCanvas(){
        animatedArrows.forEach {
            it.timer.stop()
            it.timeline.stop()
        }
        arrows.clear()
        animatedArrows.clear()
        gc.clearRect(0.0, 0.0, width, height)
    }

    private fun drawArrow(fromX: Double, toX: Double, y: Double, message: String) {
        var tFromX = fromX
        var tToX = toX
        var isRight = true
        if (tFromX < tToX) {
            tFromX += 5
            tToX -= 5
            gc.clearRect(
                0.0,
                y - 4,
                width,
                arrowHeight + 8
            )
            if (tFromX > tToX)
                tToX = tFromX
        } else {
            tFromX -= 5
            tToX += 5
            gc.clearRect(
                0.0,
                y - 4,
                width,
                arrowHeight + 8
            )
            if (tFromX < tToX)
                tFromX = tToX
            isRight = false

        }
        val xPoints = mutableListOf(tFromX, tToX)
        val yPoints = mutableListOf(y + (arrowHeight / 2), y + (arrowHeight / 2))
        val textY = y + arrowHeight / 4
        val textX: Double
        yPoints.addAll(listOf(y, y + (arrowHeight / 2), y + arrowHeight))
        if (isRight) {
            xPoints.addAll(listOf(tToX - dartWidth, tToX + 2, tToX - dartWidth))
            textX = tFromX + ((tToX-tFromX)/2)
        } else {
            xPoints.addAll(listOf(tToX + dartWidth, tToX - 2, tToX + dartWidth))
            textX = tToX + ((tFromX-tToX)/2)
        }
        gc.strokePolyline(xPoints.toDoubleArray(), yPoints.toDoubleArray(), 5)
        val text = Text(message)
        text.applyCss()
        val correctTextX = text.layoutBounds.width/2
        gc.fillText(message, textX-correctTextX, textY)
    }

    data class ArrowAnimationProperties(
        val properties: ArrowProperties,
        val timeline: Timeline,
        val timer: AnimationTimer
    )

    data class ArrowProperties(
        val from: Int,
        val to: Int,
        val name: String,
        val title: String = "",
        val description: String = "",
        val example: String = ""
    )
}