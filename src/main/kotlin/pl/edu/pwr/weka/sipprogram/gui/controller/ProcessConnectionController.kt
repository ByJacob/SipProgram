package pl.edu.pwr.weka.sipprogram.gui.controller

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXComboBox
import io.datafx.controller.ViewController
import io.datafx.controller.ViewNode
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
    lateinit var requestListComboBox: JFXComboBox<RequestEnum>

    @ViewNode
    lateinit var requestListAddButton: JFXButton

    val model = ProcessConnectionModel()

    @PostConstruct
    fun init() {
        requestListComboBox.items = model.requestList
        log.debug(model.requestList.toString())
    }
}