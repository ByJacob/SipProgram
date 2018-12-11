package pl.edu.pwr.weka.sipprogram.gui.controller

import pl.edu.pwr.weka.sipprogram.util.MessageBundle
import tornadofx.*

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 09.12.2018 13:17
 */
class DictionaryController : Controller() {
    val model = DictionaryItemModel()
    val dictionaryList = mutableListOf<DictionaryItem>().observable()

    init {

        messages = MessageBundle("Dictionary")
        initDictionary()
        showSelectedItem(dictionaryList[0])
    }

    private fun initDictionary() {
        dictionaryList.addAll(
                DictionaryItem("REGISTER",
                        messages["register_methods_desc"],
                        messages["register_methods_url_name"],
                        "https://tools.ietf.org/html/rfc3261#section-10", DictionaryType.METHODS),
                DictionaryItem("ACK",
                        messages["ack_methods_desc"],
                        messages["ack_methods_url_name"],
                        "http://www.iana.org/go/rfc3261", DictionaryType.METHODS),
                DictionaryItem("BYE",
                        messages["bye_methods_desc"],
                        messages["bye_methods_url_name"],
                        "http://www.iana.org/go/rfc3261", DictionaryType.METHODS),
                DictionaryItem("CANCEL",
                        messages["cancel_methods_desc"],
                        messages["cancel_methods_url_name"],
                        "http://www.iana.org/go/rfc3261", DictionaryType.METHODS),
                DictionaryItem("INFO",
                        messages["info_methods_desc"],
                        messages["info_methods_url_name"],
                        "http://www.iana.org/go/rfc6086", DictionaryType.METHODS),
                DictionaryItem("MESSAGE",
                        messages["message_methods_desc"],
                        messages["message_methods_url_name"],
                        "http://www.iana.org/go/rfc3428", DictionaryType.METHODS),
                DictionaryItem("NOTIFY",
                        messages["notify_methods_desc"],
                        messages["notify_methods_url_name"],
                        "http://www.iana.org/go/rfc6665", DictionaryType.METHODS),
                DictionaryItem("UPDATE",
                        messages["update_methods_desc"],
                        messages["update_methods_url_name"],
                        "http://www.iana.org/go/rfc3311", DictionaryType.METHODS),
                DictionaryItem("ACK",
                        messages["ack_methods_desc"],
                        messages["ack_methods_url_name"],
                        "http://www.iana.org/go/rfc3261", DictionaryType.METHODS),
                DictionaryItem("BYE",
                        messages["bye_methods_desc"],
                        messages["bye_methods_url_name"],
                        "http://www.iana.org/go/rfc3261", DictionaryType.METHODS),
                DictionaryItem("CANCEL",
                        messages["cancel_methods_desc"],
                        messages["cancel_methods_url_name"],
                        "http://www.iana.org/go/rfc3261", DictionaryType.METHODS),
                DictionaryItem("INFO",
                        messages["info_methods_desc"],
                        messages["info_methods_url_name"],
                        "http://www.iana.org/go/rfc6086", DictionaryType.METHODS),
                DictionaryItem("MESSAGE",
                        messages["message_methods_desc"],
                        messages["message_methods_url_name"],
                        "http://www.iana.org/go/rfc3428", DictionaryType.METHODS),
                DictionaryItem("NOTIFY",
                        messages["notify_methods_desc"],
                        messages["notify_methods_url_name"],
                        "http://www.iana.org/go/rfc6665", DictionaryType.METHODS),
                DictionaryItem("OPTIONS",
                        messages["options_methods_desc"],
                        messages["options_methods_url_name"],
                        "http://www.iana.org/go/rfc3261", DictionaryType.METHODS),
                DictionaryItem("PRACK",
                        messages["prack_methods_desc"],
                        messages["prack_methods_url_name"],
                        "http://www.iana.org/go/rfc3262", DictionaryType.METHODS),
                DictionaryItem("PUBLISH",
                        messages["publish_methods_desc"],
                        messages["publish_methods_url_name"],
                        "http://www.iana.org/go/rfc3903", DictionaryType.METHODS),
                DictionaryItem("REFER",
                        messages["refer_methods_desc"],
                        messages["refer_methods_url_name"],
                        "http://www.iana.org/go/rfc3515", DictionaryType.METHODS),
                DictionaryItem("SUBSCRIBE",
                        messages["subscribe_methods_desc"],
                        messages["subscribe_methods_url_name"],
                        "http://www.iana.org/go/rfc6665", DictionaryType.METHODS),
                DictionaryItem("UPDATE",
                        messages["update_methods_desc"],
                        messages["update_methods_url_name"],
                        "http://www.iana.org/go/rfc3311", DictionaryType.METHODS),
                DictionaryItem("ACK 100(Trying)",
                        messages["ack100_responsecode_desc"],
                        messages["ack100_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21.1.1", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 180(Ringing)",
                        messages["ack180_responsecode_desc"],
                        messages["ack180_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 181(Call Is Being Forwarded)",
                        messages["ack181_responsecode_desc"],
                        messages["ack181_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 182(Queued)",
                        messages["ack182_responsecode_desc"],
                        messages["ack182_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 183(Session Progress)",
                        messages["ack183_responsecode_desc"],
                        messages["ack183_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 199(Early Dialog Terminated)",
                        messages["ack199_responsecode_desc"],
                        messages["ack199_responsecode_url_name"], "http://www.iana.org/go/rfc6228", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 200(OK)",
                        messages["ack200_responsecode_desc"],
                        messages["ack200_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 202(Accepted (Deprecated))",
                        messages["ack202_responsecode_desc"],
                        messages["ack202_responsecode_url_name"], "http://www.iana.org/go/rfc6665", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 204(No Notification)",
                        messages["ack204_responsecode_desc"],
                        messages["ack204_responsecode_url_name"], "http://www.iana.org/go/rfc5839", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 300(Multiple Choices)",
                        messages["ack300_responsecode_desc"],
                        messages["ack300_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 301(Moved Permanently)",
                        messages["ack301_responsecode_desc"],
                        messages["ack301_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 302(Moved Temporarily)",
                        messages["ack302_responsecode_desc"],
                        messages["ack302_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 305(Use Proxy)",
                        messages["ack305_responsecode_desc"],
                        messages["ack305_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 380(Alternative Service)",
                        messages["ack380_responsecode_desc"],
                        messages["ack380_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 400(Bad Request)",
                        messages["ack400_responsecode_desc"],
                        messages["ack400_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 401(Unauthorized)",
                        messages["ack401_responsecode_desc"],
                        messages["ack401_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 402(Payment Required)",
                        messages["ack402_responsecode_desc"],
                        messages["ack402_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 403(Forbidden)",
                        messages["ack403_responsecode_desc"],
                        messages["ack403_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 404(Not Found)",
                        messages["ack404_responsecode_desc"],
                        messages["ack404_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 405(Method Not Allowed)",
                        messages["ack405_responsecode_desc"],
                        messages["ack405_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 406(Not Acceptable)",
                        messages["ack406_responsecode_desc"],
                        messages["ack406_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 407(Proxy Authentication Required)",
                        messages["ack407_responsecode_desc"],
                        messages["ack407_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 408(Request Timeout)",
                        messages["ack408_responsecode_desc"],
                        messages["ack408_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 410(Gone)",
                        messages["ack410_responsecode_desc"],
                        messages["ack410_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 412(Conditional Request Failed)",
                        messages["ack412_responsecode_desc"],
                        messages["ack412_responsecode_url_name"], "http://www.iana.org/go/rfc3903", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 413(Request Entity Too Large)",
                        messages["ack413_responsecode_desc"],
                        messages["ack413_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 414(Request-URI Too Long)",
                        messages["ack414_responsecode_desc"],
                        messages["ack414_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 415(Unsupported Media Type)",
                        messages["ack415_responsecode_desc"],
                        messages["ack415_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 416(Unsupported URI Scheme)",
                        messages["ack416_responsecode_desc"],
                        messages["ack416_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 417(Unknown Resource-Priority)",
                        messages["ack417_responsecode_desc"],
                        messages["ack417_responsecode_url_name"], "http://www.iana.org/go/rfc4412", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 420(Bad Extension)",
                        messages["ack420_responsecode_desc"],
                        messages["ack420_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 421(Extension Required)",
                        messages["ack421_responsecode_desc"],
                        messages["ack421_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 422(Session Interval Too Small)",
                        messages["ack422_responsecode_desc"],
                        messages["ack422_responsecode_url_name"], "http://www.iana.org/go/rfc4028", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 423(Interval Too Brief)",
                        messages["ack423_responsecode_desc"],
                        messages["ack423_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 424(Bad Location Information)",
                        messages["ack424_responsecode_desc"],
                        messages["ack424_responsecode_url_name"], "http://www.iana.org/go/rfc6442", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 428(Use Identity Header)",
                        messages["ack428_responsecode_desc"],
                        messages["ack428_responsecode_url_name"], "http://www.iana.org/go/rfc8224", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 429(Provide Referrer Identity)",
                        messages["ack429_responsecode_desc"],
                        messages["ack429_responsecode_url_name"], "http://www.iana.org/go/rfc3892", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 430(Flow Failed)",
                        messages["ack430_responsecode_desc"],
                        messages["ack430_responsecode_url_name"], "http://www.iana.org/go/rfc5626", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 433(Anonymity Disallowed)",
                        messages["ack433_responsecode_desc"],
                        messages["ack433_responsecode_url_name"], "http://www.iana.org/go/rfc5079", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 436(Bad Identity Info)",
                        messages["ack436_responsecode_desc"],
                        messages["ack436_responsecode_url_name"], "http://www.iana.org/go/rfc8224", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 437(Unsupported Credential)",
                        messages["ack437_responsecode_desc"],
                        messages["ack437_responsecode_url_name"], "http://www.iana.org/go/rfc8224", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 438(Invalid Identity Header)",
                        messages["ack438_responsecode_desc"],
                        messages["ack438_responsecode_url_name"], "http://www.iana.org/go/rfc8224", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 439(First Hop Lacks Outbound Support)",
                        messages["ack439_responsecode_desc"],
                        messages["ack439_responsecode_url_name"], "http://www.iana.org/go/rfc5626", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 440(Max-Breadth Exceeded)",
                        messages["ack440_responsecode_desc"],
                        messages["ack440_responsecode_url_name"], "http://www.iana.org/go/rfc5393", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 469(Bad Info Package)",
                        messages["ack469_responsecode_desc"],
                        messages["ack469_responsecode_url_name"], "http://www.iana.org/go/rfc6086", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 470(Consent Needed)",
                        messages["ack470_responsecode_desc"],
                        messages["ack470_responsecode_url_name"], "http://www.iana.org/go/rfc5360", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 480(Temporarily Unavailable)",
                        messages["ack480_responsecode_desc"],
                        messages["ack480_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 481(Call/Transaction Does Not Exist)",
                        messages["ack481_responsecode_desc"],
                        messages["ack481_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 482(Loop Detected)",
                        messages["ack482_responsecode_desc"],
                        messages["ack482_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 483(Too Many Hops)",
                        messages["ack483_responsecode_desc"],
                        messages["ack483_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 484(Address Incomplete)",
                        messages["ack484_responsecode_desc"],
                        messages["ack484_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 485(Ambiguous)",
                        messages["ack485_responsecode_desc"],
                        messages["ack485_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 486(Busy Here)",
                        messages["ack486_responsecode_desc"],
                        messages["ack486_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 487(Request Terminated)",
                        messages["ack487_responsecode_desc"],
                        messages["ack487_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 488(Not Acceptable Here)",
                        messages["ack488_responsecode_desc"],
                        messages["ack488_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 489(Bad Event)",
                        messages["ack489_responsecode_desc"],
                        messages["ack489_responsecode_url_name"], "http://www.iana.org/go/rfc6665", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 491(Request Pending)",
                        messages["ack491_responsecode_desc"],
                        messages["ack491_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 493(Undecipherable)",
                        messages["ack493_responsecode_desc"],
                        messages["ack493_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 494(Security Agreement Required)",
                        messages["ack494_responsecode_desc"],
                        messages["ack494_responsecode_url_name"], "http://www.iana.org/go/rfc3329", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 500(Server Internal Error)",
                        messages["ack500_responsecode_desc"],
                        messages["ack500_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 501(Not Implemented)",
                        messages["ack501_responsecode_desc"],
                        messages["ack501_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 502(Bad Gateway)",
                        messages["ack502_responsecode_desc"],
                        messages["ack502_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 503(Service Unavailable)",
                        messages["ack503_responsecode_desc"],
                        messages["ack503_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 504(Server Time-out)",
                        messages["ack504_responsecode_desc"],
                        messages["ack504_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 505(Version Not Supported)",
                        messages["ack505_responsecode_desc"],
                        messages["ack505_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 513(Message Too Large)",
                        messages["ack513_responsecode_desc"],
                        messages["ack513_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 580(Precondition Failure)",
                        messages["ack580_responsecode_desc"],
                        messages["ack580_responsecode_url_name"], "http://www.iana.org/go/rfc3312", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 600(Busy Everywhere)",
                        messages["ack600_responsecode_desc"],
                        messages["ack600_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 603(Decline)",
                        messages["ack603_responsecode_desc"],
                        messages["ack603_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 604(Does Not Exist Anywhere)",
                        messages["ack604_responsecode_desc"],
                        messages["ack604_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 606(Not Acceptable)",
                        messages["ack606_responsecode_desc"],
                        messages["ack606_responsecode_url_name"], "https://tools.ietf.org/html/rfc3261#section-21", DictionaryType.RESPONSE_CODE),
                DictionaryItem("ACK 607(Unwanted)",
                        messages["ack607_responsecode_desc"],
                        messages["ack607_responsecode_url_name"], "http://www.iana.org/go/rfc8197", DictionaryType.RESPONSE_CODE)
                )
    }

    fun showSelectedItem(it: DictionaryItem) {
        model.name.value = it.name
        model.description.value = it.description
        model.urlName.value = it.urlName
        model.url.value = it.url
    }
}