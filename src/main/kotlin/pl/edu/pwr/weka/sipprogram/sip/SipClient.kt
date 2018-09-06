package pl.edu.pwr.weka.sipprogram.sip

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.sip.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 04.09.2018 18:41\
 * http://alex.bikfalvi.com/teaching/upf/2013/architecture_and_signaling/lab/sip/
 */
@Service
class SipClient : SipListener {

    final val LOGGER = LoggerFactory.getLogger(this::class.java)

    override fun processIOException(p0: IOExceptionEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun processDialogTerminated(p0: DialogTerminatedEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun processRequest(p0: RequestEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun processResponse(responseEvent: ResponseEvent?) {
        val response = responseEvent?.response
        LOGGER.info("Recive response: " + response.toString())
    }

    override fun processTimeout(p0: TimeoutEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun processTransactionTerminated(p0: TransactionTerminatedEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}