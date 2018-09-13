package pl.edu.pwr.weka.sipprogram.sip.request

import pl.edu.pwr.weka.sipprogram.sip.request.base.Request
import pl.edu.pwr.weka.sipprogram.sip.request.base.RequestEnum

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 11.09.2018 21:35
 */

class Register(serwerURL: String,
               localURL: String,
               userLogin: String,
               userPassword: String): Request(RequestEnum.REGISTER, serwerURL, localURL, userLogin, userLogin, userPassword) {
    init {

    }

}