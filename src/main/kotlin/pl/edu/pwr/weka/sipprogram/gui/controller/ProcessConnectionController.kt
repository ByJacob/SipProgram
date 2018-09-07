package pl.edu.pwr.weka.sipprogram.gui.controller

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXComboBox
import io.datafx.controller.ViewController
import io.datafx.controller.ViewNode
import javafx.fxml.FXML
import javax.annotation.PostConstruct

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 07.09.2018 23:01
 */

@ViewController("/fxml/ProcessConnection.fxml")
class ProcessConnectionController{

    @ViewNode
    lateinit var requestListComboBox: JFXComboBox<String>

    @ViewNode
    lateinit var requestListAddButton: JFXButton

    @PostConstruct
    fun init() {

    }
}