package pl.edu.pwr.weka.sipprogram.gui.controller

import io.datafx.controller.ViewController
import io.datafx.controller.ViewNode
import io.datafx.controller.flow.Flow
import io.datafx.controller.flow.context.FXMLViewFlowContext
import io.datafx.controller.flow.context.ViewFlowContext
import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import pl.edu.pwr.weka.sipprogram.gui.controller.base.BaseController
import pl.edu.pwr.weka.sipprogram.gui.model.MainAppModel
import javax.annotation.PostConstruct


/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 01.09.2018 12:17
 */
@ViewController(value = "/fxml/MainApp.fxml", title = "Test123121")
class MainAppController : BaseController() {

    @ViewNode
    lateinit var mainTab1: Tab

    @ViewNode
    lateinit var mainTab2: Tab

    @ViewNode
    lateinit var mainTabPane: TabPane

    @FXMLViewFlowContext
    lateinit var flowContext: ViewFlowContext

    var model = MainAppModel()

    @PostConstruct
    fun init() {
        log.debug("Initialize ${this.javaClass.simpleName}")
        val flow = Flow(ProcessConnectionController::class.java)
        val startInTab = flow.createHandler().startInTab()
        mainTabPane.tabs.clear()
        mainTabPane.tabs.add(startInTab)
    }
}
