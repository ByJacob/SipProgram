package pl.edu.pwr.weka.sipprogram.util

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 20.11.2018 23:20
 */

import javafx.animation.KeyFrame
import javafx.animation.KeyValue
import javafx.animation.Timeline
import javafx.scene.Scene
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.Text
import javafx.stage.Stage
import javafx.stage.StageStyle
import javafx.util.Duration
import tornadofx.*

object Toast {
    fun makeText(toastMsg: String, toastDelay: Int, fadeInDelay: Int, fadeOutDelay: Int) {
        val toastStage = Stage()
        val ownerStage = FX.primaryStage
        toastStage.initOwner(ownerStage)
        toastStage.isResizable = false
        toastStage.initStyle(StageStyle.TRANSPARENT)

        val text = Text(toastMsg)
        text.font = Font.font("Roboto", 25.0)
        text.fill = Color.RED

        val root = StackPane(text)
        root.style = "-fx-background-radius: 20; -fx-background-color: rgba(0, 0, 0, 0.85); -fx-padding: 50px;"
        root.opacity = 0.0

        val scene = Scene(root)
        scene.fill = Color.TRANSPARENT
        toastStage.scene = scene
        toastStage.show()

        val fadeInTimeline = Timeline()
        val fadeInKey1 = KeyFrame(Duration.millis(fadeInDelay.toDouble()), KeyValue(toastStage.scene.root.opacityProperty(), 1))
        fadeInTimeline.keyFrames.add(fadeInKey1)
        fadeInTimeline.setOnFinished { ae ->
            Thread {
                try {
                    Thread.sleep(toastDelay.toLong())
                } catch (e: InterruptedException) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                }

                val fadeOutTimeline = Timeline()
                val fadeOutKey1 = KeyFrame(Duration.millis(fadeOutDelay.toDouble()), KeyValue(toastStage.scene.root.opacityProperty(), 0))
                fadeOutTimeline.keyFrames.add(fadeOutKey1)
                fadeOutTimeline.setOnFinished { aeb -> toastStage.close() }
                fadeOutTimeline.play()
            }.start()
        }
        fadeInTimeline.play()
    }
}
