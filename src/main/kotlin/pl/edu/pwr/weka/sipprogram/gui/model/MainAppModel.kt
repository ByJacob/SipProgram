package pl.edu.pwr.weka.sipprogram.gui.model

import org.slf4j.LoggerFactory

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 01.09.2018 12:42
 */


class MainAppModel {
    private var log = LoggerFactory.getLogger(this.javaClass)

    init {
        log.debug("Initialize ${this.javaClass.simpleName}")
    }
}