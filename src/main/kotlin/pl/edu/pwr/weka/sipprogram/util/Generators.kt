package pl.edu.pwr.weka.sipprogram.util

import org.apache.commons.codec.digest.DigestUtils
import java.text.SimpleDateFormat
import java.util.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 16.09.2018 19:17
 */
class Generators {
    companion object {
        fun generateUniqueId(): Int {
            val idOne = UUID.randomUUID()
            var str = "" + idOne
            val uid = str.hashCode()
            val filterStr = "" + uid
            str = filterStr.replace("-".toRegex(), "")
            return Integer.parseInt(str)
        }
        fun calculateNonce(): String {
            val d = Date()
            val f = SimpleDateFormat("yyyy:MM:dd:hh:mm:ss")
            val fmtDate = f.format(d)
            val randomInt = Random(100000).nextInt()
            return DigestUtils.md5Hex(fmtDate + randomInt.toString())
        }
    }
}