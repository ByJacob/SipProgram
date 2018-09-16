package pl.edu.pwr.weka.sipprogram.gui.controller.item

import io.datafx.controller.ViewController
import io.datafx.controller.ViewNode
import javafx.scene.control.Label

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 16.09.2018 11:35
 */
@ViewController("/fxml/item/ProcessConnectionItem.fxml")
class ProcessConnectionItemController {

    @ViewNode
    lateinit var overline: Label
    @ViewNode
    lateinit var primaryText: Label
    @ViewNode
    lateinit var secondaryText: Label
}