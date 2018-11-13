package pl.edu.pwr.weka.sipprogram.sip.headerEnums

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 05.09.2018 20:55
 */
enum class RequestEnum(val sipName: String) {
    REGISTER("Register"),
    INVITE("Invite"),
    ACK("Ack"),
    //CANCEL("Cancel"),
    //OPTIONS("Options"),
    //BYE("Bye"),
    //REFER("Refer"),
    //SUBSCRIBE("Subscribe"),
    //NOTIFY("Notify"),
    //INFO("Info"),
    //PUBLISH("Publish"),
    //MESSAGE("Message")
    ;
    override fun toString(): String{
        return sipName.toUpperCase()
    }


}