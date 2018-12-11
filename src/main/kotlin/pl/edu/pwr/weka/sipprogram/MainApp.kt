package pl.edu.pwr.weka.sipprogram

import javafx.application.Platform
import javafx.stage.Stage
import pl.edu.pwr.weka.sipprogram.gui.view.MainView
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import pl.edu.pwr.weka.sipprogram.util.MessageBundle
import pl.edu.pwr.weka.sipprogram.util.addBetterIcon
import pl.edu.pwr.weka.sipprogram.util.jfxdecorator
import tornadofx.*


/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 01.09.2018 12:00
 */

class PreMainView : View() {
    override val root = jfxdecorator(MainView::class, fullScreen = false) {
        isCustomMaximize = true
        SipProtocol.port
    }

    init {
        title = messages["program_name"]
        root.setMaximized(true)
        root.setOnCloseButtonAction { Platform.runLater { System.exit(0) } }
        val iconPath = "images/icon.png"
        root.addBetterIcon(iconPath)
    }
}

class MainApp : App(PreMainView::class, Styles::class) {

    init {
        FX.messages = MessageBundle("Messages")
        reloadStylesheetsOnFocus()
    }

    override fun start(stage: Stage) {
        try {
            super.start(stage)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}

fun main(args: Array<String>) {
    try {
        launch<MainApp>(args)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}