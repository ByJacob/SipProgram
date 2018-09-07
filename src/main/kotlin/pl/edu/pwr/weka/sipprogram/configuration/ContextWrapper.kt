package pl.edu.pwr.weka.sipprogram.configuration

import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 07.09.2018 19:55
 */
@Service
class ContextWrapper {
    companion object {
        lateinit var context: ApplicationContext
    }
    constructor(ac: ApplicationContext){
        context = ac
    }
}