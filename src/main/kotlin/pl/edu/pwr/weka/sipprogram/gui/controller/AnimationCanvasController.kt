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
        model.item.documentationUrl = scenario.documentationUrl
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
            model.item.title = scenario.name
            model.item.description = scenario.description
            model.item.arrowExample = ""
        } else {
            model.item.title = scenario.arrows[actualCount-1].title
            model.item.description = scenario.arrows[actualCount-1].description
            model.item.arrowExample = scenario.arrows[actualCount-1].example
        }
    }

    private fun addArrow() {
        if(displayedArrow<actualCount){
            fire(AnimationCanvasFragment.NextArrowEvent(scenario.arrows[displayedArrow]))
            displayedArrow++
        }
    }
}