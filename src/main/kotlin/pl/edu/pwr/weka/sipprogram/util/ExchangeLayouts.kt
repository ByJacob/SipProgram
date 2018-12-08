package pl.edu.pwr.weka.sipprogram.util

import com.jfoenix.controls.JFXDecorator
import javafx.scene.image.Image
import javafx.scene.layout.HBox
import tornadofx.*
import kotlin.reflect.KClass

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 08.12.2018 17:30
 */

//Exchange Layout.kt from KFoenix
//https://github.com/bkenn/KFoenix/blob/master/src/main/kotlin/kfoenix/Layouts.kt

fun <T : UIComponent> View.jfxdecorator(uiComponent: T, fullScreen: Boolean = true, max: Boolean = true, min: Boolean = true, op: JFXDecorator.() -> Unit = {}) = opcr(this, JFXDecorator(primaryStage, uiComponent.root, fullScreen, max, min), op)

fun View.jfxdecorator(uiComponent: KClass<out UIComponent>, fullScreen: Boolean = true, max: Boolean = true, min: Boolean = true, op: JFXDecorator.() -> Unit = {}) = jfxdecorator(find(uiComponent), fullScreen, max, min, op)

fun JFXDecorator.addBetterIcon(iconPath: String) {
    FX.primaryStage.icons.add(Image(iconPath))
    val lookup = this.lookup(".jfx-decorator-title-container") as HBox
    lookup.children.add(0, imageview(iconPath) {
        fitHeightProperty().bind(lookup.heightProperty().subtract(5))
        fitWidthProperty().bind(lookup.heightProperty().subtract(5))
    })
}

