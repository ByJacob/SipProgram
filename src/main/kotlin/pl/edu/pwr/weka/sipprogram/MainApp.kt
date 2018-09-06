package pl.edu.pwr.weka.sipprogram

import javafx.stage.Stage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext
import tornadofx.*


/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 01.09.2018 12:00
 */
@SpringBootApplication
class MainApp : App() {

    @Autowired
    lateinit var mainAppController: MainAppController
    lateinit var context: ConfigurableApplicationContext

    override fun init() {
        context = SpringApplication.run(MainApp::class.java)
        mainAppController = context.getBean(MainAppController::class.java)
    }

    override fun start(stage: Stage) {
        mainAppController.showView(stage)
    }

    override fun stop() {
        context.close()
    }
}

fun main(args: Array<String>) {
    launch<MainApp>(args)
}