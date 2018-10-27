package pl.edu.pwr.weka.sipprogram

import kfoenix.jfxdecorator
import pl.edu.pwr.weka.sipprogram.gui2.view.MainView
import tornadofx.*


/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 01.09.2018 12:00
 */

class PreMainView : View() {
    override val root = jfxdecorator(MainView::class)
}

class MainApp : App(PreMainView::class, Styles::class) {

    override fun stop() {
        System.exit(0)
    }
}

fun main(args: Array<String>) {
    launch<MainApp>(args)
}