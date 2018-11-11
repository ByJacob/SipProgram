package pl.edu.pwr.weka.sipprogram.gui.view.fragment

import pl.edu.pwr.weka.sipprogram.gui.controller.FormRequestController
import pl.edu.pwr.weka.sipprogram.gui.model.FormRequestModel
import pl.edu.pwr.weka.sipprogram.gui.view.row.AddHeaderRowView
import pl.edu.pwr.weka.sipprogram.gui.view.row.header.HeaderRequestLineRowView
import pl.edu.pwr.weka.sipprogram.gui.view.ProcessConnectionView
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
        model.method.addListener{_, _, _ ->
            fire(ProcessConnectionView.RefreshListFormRequestEvent())
        }
    }

    init {
        val headerRequestLineRowView = HeaderRequestLineRowView()
        model.method.bind(headerRequestLineRowView.model.method)
        controller.listHeaderRowsView.add(headerRequestLineRowView)
        val addHeaderRowView = AddHeaderRowView()
        controller.listHeaderRowsView.add(addHeaderRowView)

    }

}
