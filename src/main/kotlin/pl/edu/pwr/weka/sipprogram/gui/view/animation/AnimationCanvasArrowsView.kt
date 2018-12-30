package pl.edu.pwr.weka.sipprogram.gui.view.animation

import javafx.animation.AnimationTimer
import javafx.animation.KeyFrame
import javafx.animation.KeyValue
import javafx.animation.Timeline
import javafx.beans.property.SimpleDoubleProperty
import javafx.event.EventHandler
import javafx.scene.control.Control
import javafx.scene.text.Text
import javafx.util.Duration
import pl.edu.pwr.weka.sipprogram.gui.view.ScenariosView
import pl.edu.pwr.weka.sipprogram.util.CanvasResizable

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 18.12.2018 20:08
 */

class AnimationCanvasArrowsView(sixthLayerLineY: Double, val countEndPoints: Int) : CanvasResizable() {

    val prefHeightProperty = SimpleDoubleProperty(0.0)

    private var endPointsX = listOf<Double>()

    private val arrows = mutableListOf<ArrowProperties>()
    private val animatedArrows = mutableListOf<ArrowAnimationProperties>()
    private val startArrowY = sixthLayerLineY + 10
    private val arrowHeight = 20.0
    private val spaceBetweenArrows = 20.0
    private val dartWidth = 15.0

    init {
        endPointsX = ScenariosView.calculateEndPointsX(countEndPoints, width)
        this.height = Control.USE_PREF_SIZE
    }

    override fun draw(change: DimensionChange) {
        endPointsX = ScenariosView.calculateEndPointsX(countEndPoints, width)
        if (change == DimensionChange.WIDTH) {
            animatedArrows.forEach {
                it.timer.stop()
                it.timeline.stop()
            }
            val animatedArrowsList = animatedArrows.toList()
            animatedArrows.clear()
            animatedArrowsList.forEach {
                drawArrowAnimation(it.properties)
            }
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
            KeyFrame(Duration.seconds(1.0), KeyValue(x, toX))
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
            drawArrow(fromX, toX, y, properties.name)
        }
        timer.start()
        timeline.play()
        animatedArrows.add(arrowAnimationProperties)
    }

    fun clearCanvas() {
        animatedArrows.forEach {
            it.timer.stop()
            it.timeline.stop()
        }
        arrows.clear()
        animatedArrows.clear()
        gc.clearRect(0.0, 0.0, width, height)
        prefHeightProperty.value = minHeight(0.0)
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
        val xLine = listOf(tFromX, tToX)
        val yLine = listOf(y + (arrowHeight / 2), y + (arrowHeight / 2))

        val yArrowRight = listOf(y, y + (arrowHeight / 2), y + arrowHeight)
        val xArrowRight = listOf(tToX - dartWidth, tToX + 2, tToX - dartWidth)

        val yArrowLeft = listOf(y, y + (arrowHeight / 2), y + arrowHeight)
        val xArrowLeft = listOf(tToX + dartWidth, tToX - 2, tToX + dartWidth)

        val yArrowLeft2 = listOf(y, y + (arrowHeight / 2), y + arrowHeight)
        val xArrowLeft2 = listOf(tFromX + dartWidth, tFromX - 2, tFromX + dartWidth)

        val textY = y + arrowHeight / 4
        val textX = if (isRight) {
            tFromX + ((tToX - tFromX) / 2)
        } else {
            tToX + ((tFromX - tToX) / 2)
        }
        val oldDash = gc.lineDashes
        val oldLineWidth = gc.lineWidth
        when {
            message.contains("RTP") -> {
                gc.strokePolyline(xArrowRight.toDoubleArray(), yArrowRight.toDoubleArray(), xArrowRight.size)
                gc.strokePolyline(xArrowLeft2.toDoubleArray(), yArrowLeft2.toDoubleArray(), xArrowLeft2.size)
                gc.setLineDashes(5.0)
                gc.lineWidth = 3.5
            }
            isRight -> {
                gc.strokePolyline(xArrowRight.toDoubleArray(), yArrowRight.toDoubleArray(), xArrowRight.size)
                gc.setLineDashes(10.0)
            }
            else -> {
                gc.strokePolyline(xArrowLeft.toDoubleArray(), yArrowLeft.toDoubleArray(), xArrowLeft.size)
                gc.setLineDashes(10.0)
            }
        }
        gc.strokePolyline(xLine.toDoubleArray(), yLine.toDoubleArray(), xLine.size)
        gc.lineWidth = oldLineWidth
        oldDash?.let {
            gc.setLineDashes(*it)
        } ?: run {
            gc.setLineDashes()
        }
        val text = Text(message)
        text.applyCss()
        val correctTextX = text.layoutBounds.width / 2
        gc.fillText(message, textX - correctTextX, textY)
        prefHeightProperty.value = minHeight(0.0)
    }

    override fun minHeight(width: Double): Double {
        return startArrowY + ((arrows.size + animatedArrows.size) * (arrowHeight + spaceBetweenArrows)) + (arrowHeight * 2)
    }

    override fun prefHeight(width: Double): Double {
        return minHeight(width)
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