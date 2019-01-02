package pl.edu.pwr.weka.sipprogram.sip

import java.net.DatagramSocket
import java.net.InetAddress
import java.net.Socket
import java.net.SocketException
import java.util.*
import javax.sip.*
import javax.sip.address.AddressFactory
import javax.sip.header.HeaderFactory
import javax.sip.message.MessageFactory


/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 05.11.2018 20:28
 * https://www.oracle.com/technetwork/java/introduction-jain-sip-090386.html
 */

object SipProtocol {
    val ip: String = getLocalAddress()
    val protocol = "udp"
    var port: Int = getLocalPort()

    private val sipFactory: SipFactory = SipFactory.getInstance()
    private lateinit var sipStack: SipStack
    private val properties = Properties()
    val sipClient = SipClient()
    lateinit var messageFactory: MessageFactory
    lateinit var headerFactory: HeaderFactory
    lateinit var addressFactory: AddressFactory
    lateinit var sipProvider: SipProvider

    init {
        properties.setProperty("javax.sip.STACK_NAME", "PWR")
        resetFactory()
    }

    fun resetFactory() {
        if (::sipStack.isInitialized)
            sipStack.listeningPoints.forEachRemaining {
                it as ListeningPoint
                sipStack.deleteListeningPoint(it)
            }
        sipFactory.resetFactory()
        sipStack = sipFactory.createSipStack(properties)
        messageFactory = sipFactory.createMessageFactory()
        headerFactory = sipFactory.createHeaderFactory()
        addressFactory = sipFactory.createAddressFactory()
        try {
            sipProvider = sipStack.createSipProvider(sipStack.createListeningPoint(ip, port, protocol))
        } catch (e: InvalidArgumentException){
            port++
            resetFactory()
        }
        sipProvider.addSipListener(sipClient)
    }

    private fun getLocalAddress(): String {
        var ipTmp = InetAddress.getLocalHost().hostAddress
        DatagramSocket().use { socket ->
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002)
            ipTmp = socket.localAddress.hostAddress
        }
        return ipTmp
    }

    private fun getLocalPort(): Int {
        var port = 5060
        var result = true
        do {
            try {
                Socket(ip, port).close() // Successful connection means the port is taken.
                result = false
            } catch (e: SocketException) {
                port++ // Could not connect.
            }
        } while (!result)
        return port
    }
}