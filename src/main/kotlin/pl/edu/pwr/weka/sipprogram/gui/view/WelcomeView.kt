package pl.edu.pwr.weka.sipprogram.gui.view

import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class WelcomeView : View(FX.messages["welcome_window"]) {
    override val root = borderpane {
        style {
            padding = box(10.px)
        }
        center {
            vbox {
                alignment = Pos.CENTER
                spacing = 5.0
                style {
                    padding = box(10.px)
                }
                imageview("images/pwr.png") {
                    fitHeight = 200.0
                    fitWidth = 200.0
                    isPreserveRatio = true
                }
                label(messages["welcome_message"]) {
                    style {
                        fontSize = 40.px
                    }
                }
                label(messages["description_about_program"]) {
                    prefWidth = 500.0
                    maxWidth = 1000.0
                    isWrapText = true
                    style {
                        fontSize = 25.px
                    }
                }
            }
        }
        right {
            vbox {
                alignment = Pos.TOP_CENTER
                spacing = 10.0
                label(messages["use_tech"]) {
                    style {
                        fontSize = 23.px
                        fontWeight = FontWeight.BOLD
                    }
                }
                label("JAIN SIP API") {
                    style {
                        fontSize = 40.px
                        textFill = Color.DARKBLUE
                    }
                }
                imageview("images/kotlin.png") {
                    fitWidthProperty().bind(this@vbox.widthProperty().multiply(0.7))
                    isPreserveRatio = true
                }
                imageview("images/javafx.png") {
                    fitWidthProperty().bind(this@vbox.widthProperty().multiply(0.7))
                    isPreserveRatio = true
                }
                imageview("images/tornadofx.png") {
                    fitWidthProperty().bind(this@vbox.widthProperty().multiply(0.7))
                    isPreserveRatio = true
                }
                imageview("images/jfoenix.png") {
                    fitWidthProperty().bind(this@vbox.widthProperty().multiply(0.7))
                    isPreserveRatio = true
                }
                label("KFoenix") {
                    style {
                        fontSize = 40.px
                        textFill = Color.DARKBLUE
                    }
                }
            }
        }
    }

}
