package pl.edu.pwr.weka.sipprogram.gui.view

import kfoenix.jfxlistview
import pl.edu.pwr.weka.sipprogram.gui.controller.AnimationCanvasController
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 17.12.2018 15:32
 */
class AnimationCanvasView : View() {

    companion object {
        fun calculateEndPointsX(countEndPoints: Int, width: Double): List<Double> {
            val endPointsX = mutableListOf<Double>()
            endPointsX.add(30.0)
            when (countEndPoints) {
                3 -> {
                    endPointsX.add(width / 2)
                }

                4 -> {
                    endPointsX.add((width - 60) / 3 + 30)
                    endPointsX.add((width - 60) / 3 * 2 + 30)
                }
            }
            endPointsX.add(width - 30)
            return endPointsX.toList()
        }
    }

    val controller: AnimationCanvasController by inject()

    override val root = borderpane {
        prefWidth = 500.0
        prefHeight = 400.0
        left{
            jfxlistview<AnimationCanvasFragment.ScenariosProperties> {
                items = controller.scenarios.observable()
            }
        }
        center{
            vbox {
                replaceChildren(find<AnimationCanvasFragment>("scenario" to controller.scenarios[0]))
                subscribe<OpenScenario> {
                    replaceChildren(find<AnimationCanvasFragment>("scenario" to it.scenario))
                }
            }
        }
    }

    class OpenScenario(val scenario: AnimationCanvasFragment.ScenariosProperties): FXEvent()
}