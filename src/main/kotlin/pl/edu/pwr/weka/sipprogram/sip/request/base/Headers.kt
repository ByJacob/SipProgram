package pl.edu.pwr.weka.sipprogram.sip.request.base

import gov.nist.javax.sip.header.Via
import gov.nist.javax.sip.header.ViaList
import pl.edu.pwr.weka.sipprogram.sip.SipProtocol
import pl.edu.pwr.weka.sipprogram.sip.headerEnums.RequestEnum
import pl.edu.pwr.weka.sipprogram.sip.request.Authorization
import javax.sip.header.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 05.11.2018 22:07
 */
class Headers {

    companion object {
        fun createViaHeaders(localIpAddress: String, localPort: Int, branchName: String): ViaList {
            val viaList = ViaList()
            if (branchName.isEmpty())
                viaList.add(SipProtocol.headerFactory
                        .createViaHeader(localIpAddress, localPort, "udp", null) as Via)
            else
                viaList.add(SipProtocol.headerFactory
                        .createViaHeader(localIpAddress, localPort, "udp", branchName) as Via)
            return viaList
        }

        fun createMaxForwardersHeader(maxForwards: Int): MaxForwardsHeader {
            return SipProtocol.headerFactory.createMaxForwardsHeader(maxForwards)
        }

        fun createFromHeader(user: String, ipAddress: String, tag: String = ""): FromHeader {
            return if (tag.isEmpty())
                SipProtocol.headerFactory.createFromHeader(
                        SipProtocol.addressFactory.createAddress("sip:$user@$ipAddress"), null)
            else
                SipProtocol.headerFactory.createFromHeader(
                        SipProtocol.addressFactory.createAddress("sip:$user@$ipAddress"), tag)
        }

        fun createToHeader(user: String, ipAddress: String, tag: String = ""): ToHeader {
            return if (tag.isEmpty())
                SipProtocol.headerFactory.createToHeader(
                        SipProtocol.addressFactory.createAddress("sip:$user@$ipAddress"), null)
            else
                SipProtocol.headerFactory.createToHeader(
                        SipProtocol.addressFactory.createAddress("sip:$user@$ipAddress"), tag)
        }

        fun createCallIdHeader(callId: String): CallIdHeader {
            return SipProtocol.headerFactory.createCallIdHeader(callId)
        }

        fun createCSeqHeader(sequenceNumber: Long, method: RequestEnum): CSeqHeader {
            return SipProtocol.headerFactory.createCSeqHeader(sequenceNumber, method.name)
        }

        fun createContactHeader(expires: Int, user: String, ipAddress: String, port: Int): ContactHeader {
            val createContactHeader = SipProtocol.headerFactory
                    .createContactHeader(SipProtocol.addressFactory.createAddress("sip:$user@$ipAddress:$port"))
            createContactHeader.expires = expires
            return createContactHeader
        }

        fun createUserAgentHeader(): UserAgentHeader {
            return SipProtocol.headerFactory.createUserAgentHeader(mutableListOf("SipProgram/JakubRosa/PWR"))
        }

        fun createAllowHeader(): AllowHeader {
            return SipProtocol.headerFactory.createAllowHeader(
                    RequestEnum.values().joinToString { it.toString().toUpperCase() }
            )
        }

        fun createAuthorizationHeader(authorization: Authorization): AuthorizationHeader {
            return authorization.takeHeader()
        }
    }
}