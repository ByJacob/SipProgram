package pl.edu.pwr.weka.sipprogram.sip.headerEnums

import javax.sip.message.Request

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 05.09.2018 20:55
 */
enum class RequestEnum(val sipName: String) {
    REGISTER(Request.REGISTER),
    INVITE(Request.INVITE),
    ACK(Request.ACK),
    CANCEL(Request.CANCEL),
    OPTIONS(Request.OPTIONS),
    BYE(Request.BYE),
    REFER(Request.REFER),
    SUBSCRIBE(Request.SUBSCRIBE),
    NOTIFY(Request.NOTIFY),
    INFO(Request.INFO),
    PUBLISH(Request.PUBLISH),
    MESSAGE(Request.MESSAGE)
    ;
    override fun toString(): String{
        return sipName
    }


}