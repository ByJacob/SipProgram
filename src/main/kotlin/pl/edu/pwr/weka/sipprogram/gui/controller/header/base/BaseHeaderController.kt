package pl.edu.pwr.weka.sipprogram.gui.controller.header.base

import tornadofx.*
import javax.sip.header.Header

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 11.11.2018 00:31
 */
abstract class BaseHeaderController : Controller() {

    abstract val model: ViewModel

    abstract fun toSipHeader(): Header
}