package pl.edu.pwr.weka.sipprogram.configuration

import javafx.fxml.FXMLLoader
import javafx.util.Callback
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.getBean
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.net.Inet4Address
import java.util.*
import javax.sip.ListeningPoint
import javax.sip.SipFactory
import javax.sip.SipProvider
import javax.sip.SipStack
import javax.sip.address.Address
import javax.sip.address.AddressFactory
import javax.sip.header.ContactHeader
import javax.sip.header.HeaderFactory
import javax.sip.message.MessageFactory

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 01.09.2018 13:37
 */

@Configuration
class Config {

    @Bean
    fun getFxmlLoader(context: ApplicationContext): FXMLLoader {
        val loader = FXMLLoader()
        loader.controllerFactory = Callback<Class<*>, Any> { context.getBean() }
        return loader
    }

    /*** Beans for SIP protocol ***/


    private val port = 8080
    private val protocol = "udp"

    @Bean
    fun getSipFactor(): SipFactory {
        return SipFactory.getInstance()
    }

    @Bean("sipProperties")
    fun getSipProperties(): Properties {
        val properties = Properties()
        properties.setProperty("javax.sip.STACK_NAME", "stack")
        return properties
    }

    @Bean
    fun getSipStack(sipFactory: SipFactory, @Qualifier("sipProperties") properties: Properties): SipStack {
        return sipFactory.createSipStack(properties)
    }

    @Bean
    fun getMessageFactory(sipFactory: SipFactory): MessageFactory {
        return sipFactory.createMessageFactory()
    }

    @Bean
    fun getHeaderFactory(sipFactory: SipFactory): HeaderFactory {
        return sipFactory.createHeaderFactory()
    }

    @Bean
    fun getAddressFactory(sipFactory: SipFactory): AddressFactory {
        return sipFactory.createAddressFactory()
    }

    @Bean("localIpAddress")
    fun getLocalIpAddress(): String {
        return "192.168.1.104"
    }

    @Bean
    fun getListeningPoint(sipStack: SipStack, @Qualifier("localIpAddress") ipAddress: String): ListeningPoint {
        return sipStack.createListeningPoint(ipAddress, port, protocol)
    }

    @Bean
    fun getSipProvider(sipStack: SipStack, listeningPoint: ListeningPoint): SipProvider {
        return sipStack.createSipProvider(listeningPoint)
    }

    @Bean
    fun getContactAddress(addressFactory: AddressFactory, @Qualifier("localIpAddress") ipAddress: String): Address {
        return addressFactory.createAddress("sip:333@$ipAddress:$port")
    }

    @Bean
    fun getContactHeader(headerFactory: HeaderFactory, contactAddress: Address): ContactHeader {
        return headerFactory.createContactHeader(contactAddress)
    }

}