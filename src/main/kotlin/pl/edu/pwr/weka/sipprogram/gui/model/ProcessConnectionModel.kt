package pl.edu.pwr.weka.sipprogram.gui.model

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import pl.edu.pwr.weka.sipprogram.gui.fx.ProcessConnectionFx
import pl.edu.pwr.weka.sipprogram.sip.request.base.RequestEnum

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 07.09.2018 23:07
 */
class ProcessConnectionModel {

    val processConnectionFx = ProcessConnectionFx()
    val requestList = FXCollections.observableArrayList<RequestEnum>()

    init {
        RequestEnum.values().forEach { requestList.add(it) }
    }

}