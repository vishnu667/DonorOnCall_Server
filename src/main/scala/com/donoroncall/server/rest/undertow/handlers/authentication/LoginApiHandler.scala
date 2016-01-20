package com.donoroncall.server.rest.undertow.handlers.authentication

import com.donoroncall.server.rest.controllers.AuthenticationController
import io.undertow.server.{HttpHandler, HttpServerExchange}
import org.apache.commons.io.IOUtils
import org.slf4j.{Logger, LoggerFactory}
import spray.json._

/**
  * Created by vishnu on 20/1/16.
  */
class LoginApiHandler extends HttpHandler {
  val LOG: Logger = LoggerFactory.getLogger(this.getClass)

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

        val userId = AuthenticationController.login(userName, password)

        if (userId != 0) {
          //TODO add logic for Authenticated User
          exchange.getResponseSender.send(JsObject(
            "status" -> JsString("ok"),
            "message" -> JsString("Login successful")
          ).prettyPrint)

        } else {
          //TODO add logic for Login failure
          exchange.getResponseSender.send(JsObject(
            "status" -> JsString("failed"),
            "message" -> JsString("Invalid userName and password")
          ).prettyPrint)
        }
      }
    }

  }
}
