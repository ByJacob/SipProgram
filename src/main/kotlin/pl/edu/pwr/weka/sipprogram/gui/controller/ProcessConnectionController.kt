package pl.edu.pwr.weka.sipprogram.gui.controller

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXComboBox
import io.datafx.controller.ViewController
import io.datafx.controller.ViewNode
import io.datafx.controller.flow.Flow
import io.datafx.controller.flow.action.ActionMethod
import io.datafx.controller.flow.action.ActionTrigger
import javafx.scene.layout.BorderPane
import pl.edu.pwr.weka.sipprogram.gui.controller.base.BaseController
import pl.edu.pwr.weka.sipprogram.gui.model.ProcessConnectionModel
import pl.edu.pwr.weka.sipprogram.sip.request.base.RequestEnum
import javax.annotation.PostConstruct

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 07.09.2018 23:01
 */

@ViewController(value="/fxml/ProcessConnection.fxml", title = "ProcessConnection")
class ProcessConnectionController : BaseController() {

    @ViewNode
    lateinit var controllerBorderPane: BorderPane

    @ViewNode
    @ActionTrigger("createRequestForm")
    lateinit var requestListAddButton: JFXButton

    @ViewNode
    @ActionTrigger("createRequestForm")
    lateinit var sendButton: JFXButton

    val model = ProcessConnectionModel()

    @PostConstruct
    fun init() {
    }

    @ActionMethod("createRequestForm")
    fun createRequestForm(){
        val flowHandler = Flow(FormRequestController::class.java).createHandler()
        controllerBorderPane.center = flowHandler.start()
    }
}