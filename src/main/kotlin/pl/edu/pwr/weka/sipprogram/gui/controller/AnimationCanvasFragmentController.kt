package pl.edu.pwr.weka.sipprogram.gui.controller

import com.beust.klaxon.Klaxon
import pl.edu.pwr.weka.sipprogram.gui.model.AnimationCanvasFragmentModel
import pl.edu.pwr.weka.sipprogram.gui.view.AnimationCanvasFragment
import pl.edu.pwr.weka.sipprogram.util.getResourceList
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 19.12.2018 22:28
 */
class AnimationCanvasFragmentController: Controller() {

    val model = AnimationCanvasFragmentModel()

    val scenario : AnimationCanvasFragment.ScenariosProperties by param()

    fun controlAnimation(isNext: Boolean){

    }

    fun resetAnimation(){

    }
}