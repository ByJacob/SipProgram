package pl.edu.pwr.weka.sipprogram.gui.controller

import io.datafx.controller.ViewController
import org.slf4j.LoggerFactory
import pl.edu.pwr.weka.sipprogram.gui.model.MainAppModel
import pl.edu.pwr.weka.sipprogram.configuration.ContextWrapper

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 01.09.2018 12:17
 */
@ViewController("/fxml/MainApp.fxml")
class MainAppController() {

    private var log = LoggerFactory.getLogger(this.javaClass)
    var model = ContextWrapper.context.getBean(MainAppModel::class.java)

    init {
        log.debug("Initialize ${this.javaClass.simpleName}")
    }
}
