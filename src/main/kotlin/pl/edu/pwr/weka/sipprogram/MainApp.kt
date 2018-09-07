package pl.edu.pwr.weka.sipprogram

import com.jfoenix.controls.JFXDecorator
import io.datafx.controller.flow.Flow
import io.datafx.controller.flow.container.DefaultFlowContainer
import io.datafx.controller.flow.context.FXMLViewFlowContext
import io.datafx.controller.flow.context.ViewFlowContext
import javafx.scene.Scene
import javafx.stage.Screen
import javafx.stage.Stage
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext
import pl.edu.pwr.weka.sipprogram.gui.controller.MainAppController
import tornadofx.*


/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 01.09.2018 12:00
 */
@SpringBootApplication
class MainApp : App() {

    lateinit var context: ConfigurableApplicationContext

    @FXMLViewFlowContext
    lateinit var flowContext: ViewFlowContext

    override fun init() {
        context = SpringApplication.run(MainApp::class.java)
    }

    override fun start(stage: Stage) {
        val flow = Flow(MainAppController::class.java)
        val flowContainer = DefaultFlowContainer()
        flowContext = ViewFlowContext()
        flowContext.register("Stage", stage)
        flow.createHandler(flowContext).start(flowContainer)

        val decorator = JFXDecorator(stage, flowContainer.view, false, true, true)
        decorator.isCustomMaximize = true
        var width = 800.0
        var height = 600.0
        try {
            val bounds = Screen.getScreens()[0].bounds
            width = bounds.width / 2.5
            height = bounds.height / 1.35
        } catch (e: Exception) {
        }

        val scene = Scene(decorator, width, height)
        scene.stylesheets.addAll(javaClass.getResource("/css/sipprogram-main.css").toExternalForm(),
                javaClass.getResource("/css/jfoenix-design.css").toExternalForm(),
                javaClass.getResource("/css/jfoenix-fonts.css").toExternalForm())
        stage.title = "LearnSip"
        stage.scene = scene
        stage.show()
    }

    override fun stop() {
        context.close()
        System.exit(0)
    }
}

fun main(args: Array<String>) {
    launch<MainApp>(args)
}