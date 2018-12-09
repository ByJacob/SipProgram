package pl.edu.pwr.weka.sipprogram.gui.view

import pl.edu.pwr.weka.sipprogram.gui.controller.FormRequestController
import pl.edu.pwr.weka.sipprogram.gui.model.FormRequestModel
import pl.edu.pwr.weka.sipprogram.gui.view.header.*
import pl.edu.pwr.weka.sipprogram.gui.view.row.AddHeaderRowView
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 27.10.2018 16:13
 */
class FormRequestFragment : Fragment("FormRequestFragment") {

    val controller: FormRequestController by inject()
    val model = FormRequestModel()

    override val root = vbox {
        vbox{
            clear()
            controller.listHeaderRowsNode = children
        }
        val addHeaderRowView = AddHeaderRowView()
        addHeaderRowView.root.managedProperty().bind(model.isSendingRequest)
        addHeaderRowView.root.visibleProperty().bind(model.isSendingRequest)
        add(addHeaderRowView)
    }

    init {
        controller.model = model
        model.method.addListener { _, _, _ ->
            fire(ProcessConnectionView.RefreshListFormRequestEvent())
        }
        val headerRequestLineRowView = HeaderRequestLineRowView()
        model.method.bind(headerRequestLineRowView.controller.model.method)
        controller.addHeader(headerRequestLineRowView)
        controller.addHeader(HeaderFromRowView())
        controller.addHeader(HeaderToRowView())
        controller.addHeader(HeaderViaRowView())
        controller.addHeader(HeaderCallIdRowView())
        controller.addHeader(HeaderCSeqRowView())


    }

}
