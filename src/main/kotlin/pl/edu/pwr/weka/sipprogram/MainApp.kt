package pl.edu.pwr.weka.sipprogram

import javafx.application.Platform
import javafx.stage.Stage
import kfoenix.jfxdecorator
import pl.edu.pwr.weka.sipprogram.gui.view.MainView
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import tornadofx.*
import java.lang.Exception


/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 01.09.2018 12:00
 */

class PreMainView : View() {
    override val root = jfxdecorator(MainView::class) {
        isCustomMaximize = true
        SipProtocol.port
    }
}

class MainApp : App(PreMainView::class, Styles::class) {

    init {
        reloadStylesheetsOnFocus()
    }

    override fun start(stage: Stage) {
        try {
            super.start(stage)
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    override fun stop() {
        super.stop()
        Platform.runLater { System.exit(0) }
    }
}

fun main(args: Array<String>) {
    try {
        launch<MainApp>(args)
    } catch (e: Exception){
        e.printStackTrace()
    }
}