package pl.edu.pwr.weka.sipprogram.gui.controller.animation

import javafx.collections.ObservableList
import javafx.scene.Node
import javafx.scene.layout.VBox
import pl.edu.pwr.weka.sipprogram.gui.model.AnimationBaseModel
import pl.edu.pwr.weka.sipprogram.gui.view.animation.AnimationCaseEnum
import pl.edu.pwr.weka.sipprogram.gui.view.animation.AnimationViewObject
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 28.11.2018 11:47
 */
class AnimationBasicController : Controller() {

    val model: AnimationBaseModel by inject()

    private val parameters: List<AnimationCaseEnum.AnimationCaseEnumParameters> by param(listOf())


    lateinit var flowLines: ObservableList<Node>

    fun resetCenter() {
        flowLines.clear()
        val vBox = VBox()
        vBox.prefHeight = 120.0
        vBox.maxHeight = 120.0
        vBox.minHeight = 120.0
        flowLines.add(vBox)
    }

    fun controlCenter(actualRequest: Int): Int {
        parameters.getOrNull(actualRequest)?.let {
            val (title, description, arrowName, isArrowRight) = parameters[actualRequest]
            if (flowLines.size < actualRequest + 1)
                flowLines.add(AnimationViewObject.requestNode(arrowName, isArrowRight))
            model.descriptionTitle.value = title
            model.description.value = description
        }
        return when (actualRequest) {
            in 0 until parameters.size -> actualRequest
            in parameters.size..Int.MAX_VALUE -> parameters.size - 1
            else -> 0
        }
    }
}