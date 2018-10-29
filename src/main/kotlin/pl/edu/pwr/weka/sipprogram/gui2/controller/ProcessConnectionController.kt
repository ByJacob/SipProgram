package pl.edu.pwr.weka.sipprogram.gui2.controller

import javafx.scene.Node
import javafx.scene.layout.BorderPane
import javafx.scene.layout.StackPane
import pl.edu.pwr.weka.sipprogram.gui2.model.FormBaseRequest
import pl.edu.pwr.weka.sipprogram.gui2.model.FormBaseRequestModel
import pl.edu.pwr.weka.sipprogram.gui2.model.item.PrepareRequestFx
import pl.edu.pwr.weka.sipprogram.gui2.model.item.ProcessConnectionModel
import pl.edu.pwr.weka.sipprogram.gui2.view.fragment.FormRequestFragment
import pl.edu.pwr.weka.sipprogram.sip.SipClient
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 22.10.2018 22:00
 */

class ProcessConnectionController : Controller() {

    val sipClient = SipClient()

    val model = ProcessConnectionModel(PrepareRequestFx(mutableListOf()))
    val createRequestForm
        get() = FormBaseRequestModel(FormBaseRequest())
    val sendAllRequest = {}

}