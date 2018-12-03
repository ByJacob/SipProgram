package pl.edu.pwr.weka.sipprogram.gui.view

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
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
            }
        }
        bottom {
            hbox {
                label("Label")
            }
        }
    }
}
