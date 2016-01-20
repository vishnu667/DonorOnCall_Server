package com.donoroncall.server.rest.undertow.handlers.authentication

import com.donoroncall.server.rest.controllers.AuthenticationController
import io.undertow.server.{HttpHandler, HttpServerExchange}
import org.apache.commons.io.IOUtils
import spray.json._

/**
  * Created by vishnu on 20/1/16.
  */
class RegistrationApiHandler extends HttpHandler {
  override def handleRequest(exchange: HttpServerExchange): Unit = {
    if (exchange.isInIoThread) {
      exchange.dispatch(this)
    } else {
      try {
        exchange.startBlocking()
        val request = new String(IOUtils.toByteArray(exchange.getInputStream))

        val requestJson = request.parseJson.asJsObject

        val userName = requestJson.getFields("userName").head.asInstanceOf[JsString].value
        val password = requestJson.getFields("password").head.asInstanceOf[JsString].value
        val email = requestJson.getFields("email").head.asInstanceOf[JsString].value

        val userId = AuthenticationController.addNewUser(userName, password, email)

        if (userId) {
          //TODO add logic for Successful Registration
          exchange.getResponseSender.send(JsObject(
            "status" -> JsString("ok"),
            "message" -> JsString("Registration successful")
          ).prettyPrint)

        } else {
          //TODO add logic for Failed Registration
          exchange.getResponseSender.send(JsObject(
            "status" -> JsString("failed"),
            "message" -> JsString("Registration Failed")
          ).prettyPrint)
        }


      }
    }

  }
}
