package com.donoroncall.server.rest.undertow.handlers.user

import com.donoroncall.server.rest.controllers.authentication.SessionHandler
import com.google.inject.Inject
import io.undertow.server.{HttpServerExchange, HttpHandler}
import org.apache.commons.io.IOUtils
import org.slf4j.{Logger, LoggerFactory}
import spray.json._


/**
  * Created by vishnu on 30/1/16.
  */
class GetUserHandler @Inject()(sessionHandler: SessionHandler) extends HttpHandler {

  override def handleRequest(exchange: HttpServerExchange): Unit = {

    if (exchange.isInIoThread) {
      exchange.dispatch(this)
    } else {
      try {

        exchange.startBlocking()
        val requestJson = new String(IOUtils.toByteArray(exchange.getInputStream)).parseJson.asJsObject
        val authToken = requestJson.getFields("token").head.asInstanceOf[JsString].value
        val user = sessionHandler.getUserForSession(authToken)

        if (user != null) {
          exchange.getResponseSender.send(JsObject(
            "status" -> JsString("ok"),
            "user" -> user.toJson
          ).prettyPrint)
        } else {
          exchange.getResponseSender.send(JsObject(
            "status" -> JsString("failed"),
            "message" -> JsString("auth Failed")
          ).prettyPrint)

        }

      } catch {
        case e: Exception =>
          exchange.getResponseSender.send(JsObject(
            "status" -> JsString("failed"),
            "message" -> JsString("Server Exception")
          ).prettyPrint)
      }
    }

  }
}
