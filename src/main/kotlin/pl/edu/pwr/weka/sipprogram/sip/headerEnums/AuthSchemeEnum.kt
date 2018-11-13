package pl.edu.pwr.weka.sipprogram.sip.headerEnums

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 07.10.2018 13:28
 */
enum class AuthSchemeEnum(val sipName: kotlin.String) {

    DIGEST("Digest");

    override fun toString(): String {
        return sipName
    }
}