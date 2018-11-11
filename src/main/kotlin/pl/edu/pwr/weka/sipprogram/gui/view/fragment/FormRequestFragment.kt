package pl.edu.pwr.weka.sipprogram.gui.view.fragment

import pl.edu.pwr.weka.sipprogram.gui.controller.FormRequestController
import pl.edu.pwr.weka.sipprogram.gui.model.FormRequestModel
import pl.edu.pwr.weka.sipprogram.gui.view.ProcessConnectionView
import pl.edu.pwr.weka.sipprogram.gui.view.row.AddHeaderRowView
import pl.edu.pwr.weka.sipprogram.gui.view.row.header.*
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
        clear()
        controller.listHeaderRowsNode = children
    }

    init {
        controller.model = model
        model.method.addListener { _, _, _ ->
            fire(ProcessConnectionView.RefreshListFormRequestEvent())
        }
        val headerRequestLineRowView = HeaderRequestLineRowView()
        model.method.bind(headerRequestLineRowView.model.method)
        controller.listHeaderRowsView.add(headerRequestLineRowView)
        controller.listHeaderRowsView.add(HeaderFromRowView())
        controller.listHeaderRowsView.add(HeaderToRowView())
        controller.listHeaderRowsView.add(HeaderViaRowView())
        controller.listHeaderRowsView.add(HeaderCallIdRowView())
        controller.listHeaderRowsView.add(HeaderCSeqRowView())
        val addHeaderRowView = AddHeaderRowView()
        controller.listHeaderRowsNode.add(addHeaderRowView.root)


    }

}
