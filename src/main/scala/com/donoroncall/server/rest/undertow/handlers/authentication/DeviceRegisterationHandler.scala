package com.donoroncall.server.rest.undertow.handlers.authentication


import com.donoroncall.server.rest.controllers.authentication.EditProfileController
import com.google.inject.Inject
import io.undertow.server.{HttpServerExchange, HttpHandler}
import org.apache.commons.io.IOUtils
import spray.json._
/**
 * Created by prashanth on 4/14/16.
 */
class DeviceRegisterationHandler @Inject()(editProfileController: EditProfileController) extends HttpHandler {
  override def handleRequest(exchange: HttpServerExchange): Unit = {

    if (exchange.isInIoThread) {
      exchange.dispatch(this)
    } else {
      try {
        exchange.startBlocking()
        val request = new String(IOUtils.toByteArray(exchange.getInputStream))

        val requestJson = request.parseJson.asJsObject

        val deviceId = requestJson.getFields("deviceId").head.asInstanceOf[JsString].value
        val UserId = requestJson.getFields("UserId").head.asInstanceOf[JsString].value

        editProfileController.registerDevice(deviceId,UserId.toInt)



      }
    }
  }
}
