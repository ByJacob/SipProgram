package pl.edu.pwr.weka.sipprogram.sip.headerEnums

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 13.11.2018 22:29
 */
enum class SupportedEnum(val sipName: String){
    REPLACES("replaces"), TIMER("timer");

    override fun toString(): String{
        return sipName
    }

}