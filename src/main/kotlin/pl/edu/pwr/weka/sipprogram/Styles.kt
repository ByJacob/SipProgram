package pl.edu.pwr.weka.sipprogram

import javafx.scene.paint.Color
import kfoenix.JFXStylesheet
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 27.10.2018 23:48
 */
class Styles : JFXStylesheet() {

    companion object {
        //class
        val glyphIcon by cssclass()
        //colors
        val decoratorColor = Color.web("#5264AE").derive(-0.2)
    }

    init {
        jfxDecoratorButtonsContainer {
            backgroundColor += decoratorColor

        }
        jfxDecoratorContentContainer {
            prefHeight = 500.px
            prefWidth = 700.px
            and(resizeBorder) {
                borderColor += box(decoratorColor)
                borderWidth += box(0.px, 4.px, 4.px, 4.px)
            }
        }
        glyphIcon {
            fontFamily = "FontAwesome"
        }
    }
}