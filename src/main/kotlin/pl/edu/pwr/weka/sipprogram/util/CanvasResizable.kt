package pl.edu.pwr.weka.sipprogram.util

import javafx.scene.canvas.Canvas
import javafx.scene.shape.StrokeLineJoin

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 18.12.2018 19:50
 */
abstract class CanvasResizable : Canvas() {

    val gc = graphicsContext2D

    init {
        widthProperty().addListener { _, _, _ -> gc.clearRect(0.0, 0.0, width, height); draw(DimensionChange.WIDTH) }
        heightProperty().addListener { _, _, _ -> gc.clearRect(0.0, 0.0, width, height); draw(DimensionChange.HEIGHT) }
        gc.lineWidth = 2.0
        gc.lineJoin = StrokeLineJoin.ROUND
    }

    abstract fun draw(change: DimensionChange)


    override fun isResizable(): Boolean {
        return true
    }

    override fun minWidth(height: Double): Double {
        return 0.0
    }

    override fun minHeight(width: Double): Double {
        return 0.0
    }

    override fun prefWidth(height: Double): Double {
        return width
    }

    override fun prefHeight(width: Double): Double {
        return height
    }

    enum class DimensionChange{
        WIDTH, HEIGHT
    }
}