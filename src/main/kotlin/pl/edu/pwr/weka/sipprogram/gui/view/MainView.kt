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
class MainView : View() {
    override val root = borderpane {
        top {
            hbox {
                label("PWR")
                FontAwesomeIconView(FontAwesomeIcon.DESKTOP).apply {
                    size = 24.toString()
                }
            }
        }
        center {
            jfxtabpane {
                tab<ProcessConnectionView>()
                tab<AnimationSipView>()
            }
        }
        bottom {
            hbox {
                label("Label")
            }
        }
    }
}
