package pl.edu.pwr.weka.sipprogram

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 01.09.2018 12:42
 */

@Component
class MainAppModel {
    private var log = LoggerFactory.getLogger(this.javaClass)

    init {
        log.debug("Initialize ${this.javaClass.simpleName}")
    }
}