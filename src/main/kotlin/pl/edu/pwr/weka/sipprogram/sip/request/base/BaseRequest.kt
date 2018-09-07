package pl.edu.pwr.weka.sipprogram.sip.request.base

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 07.09.2018 23:27
 */
abstract class BaseRequest {
    val map = HashMap<String, String>()
    abstract val method: RequestEnum
}