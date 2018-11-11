package pl.edu.pwr.weka.sipprogram.sip.request.base

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 05.09.2018 20:55
 */
enum class RequestEnum(val sipName: String) {
    REGISTER("Register"),
    INVITE("Invite"),
    ACK("Ack");

    override fun toString(): String{
        return sipName
    }


}