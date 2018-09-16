package pl.edu.pwr.weka.sipprogram.util

import java.util.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 16.09.2018 19:17
 */
class IdGenerator {
    companion object {
        fun generateUniqueId(): Int {
            val idOne = UUID.randomUUID()
            var str = "" + idOne
            val uid = str.hashCode()
            val filterStr = "" + uid
            str = filterStr.replace("-".toRegex(), "")
            return Integer.parseInt(str)
        }
    }
}