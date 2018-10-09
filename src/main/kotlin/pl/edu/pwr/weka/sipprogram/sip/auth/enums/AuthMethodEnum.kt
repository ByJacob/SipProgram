package pl.edu.pwr.weka.sipprogram.sip.auth.enums

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 07.10.2018 13:28
 */
enum class AuthMethodEnum(val prettyName: kotlin.String) {

    BASIC("Basic"), DIGEST("Digest");

    override fun toString(): String {
        return prettyName
    }
}