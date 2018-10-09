package pl.edu.pwr.weka.sipprogram.sip.auth.enums

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 07.10.2018 13:41
 */
enum class QualityOfProtectionEnum(val prettyName: String) {
    EMPTY("blank"), AUTH("auth"), AUTHINT("auth-int");

    override fun toString(): String{
        return prettyName
    }



}