package pl.edu.pwr.weka.sipprogram.sip.request.base

import javax.sip.ResponseEvent

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 16.09.2018 20:37
 */
interface ResponseListener {
    fun processResponse(re: ResponseEvent)
}