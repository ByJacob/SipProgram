package pl.edu.pwr.weka.sipprogram.gui.view

import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import kfoenix.jfxlistview
import pl.edu.pwr.weka.sipprogram.gui.controller.ScenariosController
import pl.edu.pwr.weka.sipprogram.gui.view.fragment.AnimationCanvasFragment
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 17.12.2018 15:32
 */
class ScenariosView : View(FX.messages["animation"]) {

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

    val controller: ScenariosController by inject()

    override val root = borderpane {
        prefWidth = 500.0
        prefHeight = 400.0
        left {
            jfxlistview<AnimationCanvasFragment.ScenariosProperties> {
                FX.primaryStage.addEventFilter(KeyEvent.KEY_PRESSED) {
                    if ((it.code == KeyCode.UP || it.code == KeyCode.DOWN)) {
                        val selectedIndex = when (it.code) {
                            KeyCode.UP -> if (this.selectionModel.selectedIndex <= 0) 0
                            else this.selectionModel.selectedIndex - 1
                            KeyCode.DOWN -> if (this.selectionModel.selectedIndex >= this.items.lastIndex)
                                this.items.lastIndex
                            else
                                this.selectionModel.selectedIndex + 1
                            else -> -1
                        }
                        this.selectionModel.select(selectedIndex)
                        val selectedItem = this.selectedItem
                        if (selectedItem != null)
                            fire(OpenScenario(selectedItem))
                    }
                }
                items = controller.scenarios.observable()
                onUserSelect(1) {
                    fire(OpenScenario(it))
                }
            }
        }
        center {
            vbox {
                if (controller.scenarios.size > 0)
                    replaceChildren(find<AnimationCanvasFragment>("scenario" to controller.scenarios[0]))
                subscribe<OpenScenario> {
                    replaceChildren(find<AnimationCanvasFragment>("scenario" to it.scenario))
                }
            }
        }
    }

    class OpenScenario(val scenario: AnimationCanvasFragment.ScenariosProperties) : FXEvent()
}