package pl.edu.pwr.weka.sipprogram.gui.view.row.base

import pl.edu.pwr.weka.sipprogram.gui.controller.base.BaseHeaderController
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 11.11.2018 00:21
 */
abstract class BaseHeaderView(name: String) : View(name) {
    abstract val controller: BaseHeaderController
    abstract val model: ViewModel

}