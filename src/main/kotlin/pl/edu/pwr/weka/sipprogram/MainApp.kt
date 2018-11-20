package pl.edu.pwr.weka.sipprogram

import javafx.stage.Stage
import kfoenix.jfxdecorator
import org.scenicview.ScenicView
import pl.edu.pwr.weka.sipprogram.gui.view.MainView
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import tornadofx.*


/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 01.09.2018 12:00
 */

class PreMainView : View() {
    override val root = jfxdecorator(MainView::class){
        isCustomMaximize = true
        SipProtocol.port
    }
}

class MainApp : App(PreMainView::class, Styles::class) {

    init {
        reloadStylesheetsOnFocus()
    }

    override fun stop() {
        System.exit(0)
    }
}

fun main(args: Array<String>) {
    launch<MainApp>(args)

}