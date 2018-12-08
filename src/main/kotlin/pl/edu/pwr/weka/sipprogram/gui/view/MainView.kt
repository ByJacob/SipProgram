package pl.edu.pwr.weka.sipprogram.gui.view

import javafx.scene.control.TextArea
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
                tab<AnimationSipView>()
                tab<ProcessConnectionView>()
                tab<DictionaryView>()
            }
        }
        bottom {
            hbox {
                label("Label")
            }
        }
    }
}
