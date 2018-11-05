package pl.edu.pwr.weka.sipprogram.gui2.model.mapper

import pl.edu.pwr.weka.sipprogram.gui2.model.FormRequest
import pl.edu.pwr.weka.sipprogram.sip.request.Request

/**
 * Project Name: sipprogram
 * User: Jakub Rosa
 * Date 29.10.2018 22:35
 */

fun FormRequest.mapToRequest(): Request {

    val response = pl.edu.pwr.weka.sipprogram.sip.request.Request()
    response.method = this.request
    response.serverAddress = this.serverAddress
    response.serverPort = this.serverPort
    response.userLogin = this.user
    response.userPassword = this.password
    response.targetUser = ""
    response.localIpAddress = this.localAddress
    response.callId = this.callId
    response.sequenceNumber = this.seqNumber
    response.fromHeaderTag = this.fromTag
    response.branchName = this.branch

    response.authorization.enabled = this.authorizationEnabled
    response.authorization.type = this.typeAuthorization
    response.authorization.realm = this.realmName
    response.authorization.algorithm = this.algorithm
    response.authorization.nonce = this.nonce
    response.authorization.qop = this.qop

    return response
}