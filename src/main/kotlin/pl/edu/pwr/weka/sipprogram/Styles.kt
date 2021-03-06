package pl.edu.pwr.weka.sipprogram

import javafx.scene.Cursor
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
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
        val jfxChipView by cssclass()
        val scrollBarAnimationDescription by cssclass()
        val containerDAnimationDescription by cssclass()
        val viewport by cssclass()
        //colors
        val decoratorColor = Color.web("#5264AE").derive(-0.2)
    }

    init {
        root {
            fontFamily = "Roboto"
            fontWeight = FontWeight.NORMAL
        }
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

        jfxChipView {
            backgroundColor += Color.WHITE
            backgroundRadius += box(16.px)
            padding = box(4.px)
        }
        scrollBarAnimationDescription {
            backgroundColor += Color.web("#00000a", 0.04)
            backgroundRadius += box(4.px)
            child(viewport){
                backgroundColor += Color.TRANSPARENT
            }
        }
        containerDAnimationDescription {
            padding = box(6.px)
            child(text){
                fill = Color.web("#000000", 0.87)
            }
        }
    }
}