package pl.edu.pwr.weka.sipprogram.sip.headerEnums

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 10.11.2018 16:25
 */
enum class TransportProtocol(val sipName: String) {
    UDP("udp"),
    TCP("tcp");

    override fun toString(): String {
        return sipName
    }


}