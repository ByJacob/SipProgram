package pl.edu.pwr.weka.sipprogram.sip


import org.slf4j.LoggerFactory
import pl.edu.pwr.weka.sipprogram.sip.request.base.ResponseListener
import javax.sip.*
import javax.sip.message.Response

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 04.09.2018 18:41\
 * http://alex.bikfalvi.com/teaching/upf/2013/architecture_and_signaling/lab/sip/
 */
class SipClient : SipListener {

    val LOGGER = LoggerFactory.getLogger(this::class.java)
    val listResponse = mutableListOf<Response>()
    val listeners = mutableListOf<ResponseListener>()

    override fun processIOException(p0: IOExceptionEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun processDialogTerminated(p0: DialogTerminatedEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun processRequest(p0: RequestEvent?) {}

    override fun processResponse(responseEvent: ResponseEvent?) {
        val response = responseEvent?.response
        response?.let { listResponse.add(it) }
        LOGGER.info("Recive response: " + response.toString())
        listeners.forEach{responseListener: ResponseListener ->
            if (responseEvent != null) {
                responseListener.processResponse(responseEvent)
            }
        }
        listeners.clear()
    }

    override fun processTimeout(p0: TimeoutEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun processTransactionTerminated(p0: TransactionTerminatedEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}