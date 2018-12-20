package pl.edu.pwr.weka.sipprogram.gui.controller

import pl.edu.pwr.weka.sipprogram.gui.model.AnimationCanvasModel
import pl.edu.pwr.weka.sipprogram.gui.view.fragment.AnimationCanvasFragment
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 19.12.2018 22:28
 */
class AnimationCanvasController : Controller() {

    val model = AnimationCanvasModel()

    val scenario: AnimationCanvasFragment.ScenariosProperties by param()

    var actualCount = 0
    var displayedArrow = 0

    init {
        displayItem()
        model.documentationUrl.value = scenario.documentationUrl
    }

    fun controlAnimation(isNext: Boolean) {
        if (isNext && actualCount < scenario.arrows.lastIndex+1) {
            actualCount++
        } else if (!isNext && actualCount > 0) {
            actualCount--
        }
        displayItem()
        addArrow()
    }

    fun resetAnimation() {
        actualCount = 0
        displayedArrow = 0
        displayItem()
        fire(AnimationCanvasFragment.ClearArrowEvent())
    }

    private fun displayItem() {
        if (actualCount == 0) {
            model.title.value = scenario.name
            model.description.value = scenario.description
            model.arrowExample.value = ""
        } else {
            model.title.value = scenario.arrows[actualCount-1].title
            model.description.value = scenario.arrows[actualCount-1].description
            model.arrowExample.value = scenario.arrows[actualCount-1].example
        }
    }

    private fun addArrow() {
        if(displayedArrow<actualCount){
            fire(AnimationCanvasFragment.NextArrowEvent(scenario.arrows[displayedArrow]))
            displayedArrow++
        }
    }
}