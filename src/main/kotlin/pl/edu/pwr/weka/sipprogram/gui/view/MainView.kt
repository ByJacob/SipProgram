package pl.edu.pwr.weka.sipprogram.gui.view

import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import kfoenix.jfxtabpane
import tornadofx.*


/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 22.10.2018 21:14
 */
class MainView : View("SIP Learn") {
    override val root = borderpane {
        center {
            jfxtabpane {
                tab<WelcomeView>()
                tab<ScenariosView>()
                tab<ProcessConnectionView>()
                tab<DictionaryView>()
                addEventFilter(KeyEvent.KEY_PRESSED) {
                    if (it.code == KeyCode.UP || it.code == KeyCode.DOWN) {
                        it.consume()
                    }
                }
            }
        }
        bottom {
            hbox {
                label("Label")
            }
        }
    }
}
