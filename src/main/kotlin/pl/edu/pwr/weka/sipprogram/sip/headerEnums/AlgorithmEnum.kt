package pl.edu.pwr.weka.sipprogram.sip.headerEnums

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 07.10.2018 14:41
 */
enum class AlgorithmEnum(val sipName: kotlin.String) {
    MD5("MD5"), MD5SESS("MD5-sess");

    override fun toString(): String{
        return sipName
    }

}