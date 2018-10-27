package pl.edu.pwr.weka.sipprogram.sip.request

import org.apache.commons.codec.digest.DigestUtils
import pl.edu.pwr.weka.sipprogram.sip.auth.enums.AlgorithmEnum
import pl.edu.pwr.weka.sipprogram.sip.auth.enums.AuthMethodEnum
import pl.edu.pwr.weka.sipprogram.sip.auth.enums.QualityOfProtectionEnum
import java.text.SimpleDateFormat
import java.util.*
import javax.sip.SipFactory
import javax.sip.header.AuthorizationHeader
import javax.sip.header.WWWAuthenticateHeader


/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 07.10.2018 13:45
 */
class Authorization {
    var enabled = false
    var type = AuthMethodEnum.DIGEST
    var realm = "asterisk"
    var algorithm = AlgorithmEnum.MD5
    var nonce = calculateNonce()
    var opaque = getOpaque(realm, nonce)
    var qops = mutableListOf<QualityOfProtectionEnum>()
    var username = "111"
    var password = "111"

    fun takeHeader(sipFactory: SipFactory): AuthorizationHeader {
        val authenticateHeader = sipFactory.createHeaderFactory().createAuthorizationHeader("")
        authenticateHeader.scheme = type.name
        authenticateHeader.realm = realm
        authenticateHeader.algorithm = algorithm.name
        authenticateHeader.nonce = nonce
        opaque = getOpaque(realm, nonce)
        authenticateHeader.opaque = opaque
        authenticateHeader.qop = qops.joinToString()
        var ha1 = DigestUtils.md5Hex("$username:$realm:$password")
        authenticateHeader.response
        return authenticateHeader
    }


    private fun getOpaque(domain: String, nonce: String): String {
        return DigestUtils.md5Hex(domain + nonce)
    }

    companion object {
        fun calculateNonce(): String {
            val d = Date()
            val f = SimpleDateFormat("yyyy:MM:dd:hh:mm:ss")
            val fmtDate = f.format(d)
            val rand = Random(100000)
            val randomInt = rand.nextInt()
            return DigestUtils.md5Hex(fmtDate + randomInt.toString())
        }
    }
}
