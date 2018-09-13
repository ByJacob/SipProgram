package pl.edu.pwr.weka.sipprogram.gui.cell

import io.datafx.controller.flow.Flow
import io.datafx.controller.flow.FlowHandler
import javafx.scene.control.ListCell
import javafx.scene.layout.StackPane
import pl.edu.pwr.weka.sipprogram.gui.controller.ProcessConnectionController

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 13.09.2018 22:27
 */
class ProcessConnectionCell: ListCell<FlowHandler>() {

    override fun updateItem(item: FlowHandler, empty: Boolean) {
        val controller = item.currentViewContext.controller as ProcessConnectionController
        
    }
}