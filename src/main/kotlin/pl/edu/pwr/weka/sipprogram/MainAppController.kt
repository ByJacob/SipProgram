package pl.edu.pwr.weka.sipprogram

import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.stage.Stage
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 01.09.2018 12:17
 */
@Component
class MainAppController(val model: MainAppModel, val loader: FXMLLoader) {
    private var log = LoggerFactory.getLogger(this.javaClass)

    init {
        log.debug("Initialize ${this.javaClass.simpleName}")
    }

    fun showView(stage: Stage) {
        loader.location = javaClass.getResource("/fxml/MainApp.fxml")
        val rootNode = loader.load<BorderPane>()
        stage.scene = Scene(rootNode)
        //stage.isMaximized  =true
        stage.show()
    }
}
