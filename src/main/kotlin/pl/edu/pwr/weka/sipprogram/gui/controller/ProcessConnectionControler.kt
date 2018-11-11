package pl.edu.pwr.weka.sipprogram.gui.controller

import pl.edu.pwr.weka.sipprogram.gui.view.fragment.FormRequestFragment
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 16:59
 */
class ProcessConnectionController : Controller() {

    val formRequestFragmentList = mutableListOf<FormRequestFragment>().observable()

    fun sendRequests() {
        formRequestFragmentList.forEach {
            val createRequest = it.controller.createRequest()
            print(createRequest.toString())
        }
    }

}
