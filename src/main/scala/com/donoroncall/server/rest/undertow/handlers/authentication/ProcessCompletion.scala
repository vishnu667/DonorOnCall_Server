package com.donoroncall.server.rest.undertow.handlers.authentication

import com.donoroncall.server.rest.controllers.authentication.AuthenticationController
import com.google.inject.Inject
import io.undertow.server.{HttpHandler, HttpServerExchange}
import org.apache.commons.io.IOUtils
import spray.json._

/**
 * Created by Anmol on 10/3/16.
 */
class ProcessCompletion @Inject()(authenticationController: AuthenticationController) extends HttpHandler {
  override def handleRequest(exchange: HttpServerExchange): Unit = {
    if (exchange.isInIoThread) {
      exchange.dispatch(this)
    } else {
      try {
        exchange.startBlocking()
        val request = new String(IOUtils.toByteArray(exchange.getInputStream))

        val requestJson = request.parseJson.asJsObject

        val userName = requestJson.getFields("userName").head.asInstanceOf[JsString].value
        val donationStatus = requestJson.getFields("donationStatus").head.asInstanceOf[JsString].value


        val userId = authenticationController.processComplete(userName)

        if (userId) {

          exchange.getResponseSender.send(JsObject(
            "status" -> JsString("Complete"),
            "message" -> JsString("Donation Process Complete.")
          ).prettyPrint)

        } else {
          //TODO add logic for Failed Registration
          exchange.getResponseSender.send(JsObject(
            "status" -> JsString("failed"),
            "message" -> JsString("Process Completion Failed")
          ).prettyPrint)
        }


      } catch {
        case e: Exception => {
          exchange.getResponseSender.send(JsObject(
            "status" -> JsString("failed"),
            "message" -> JsString("Process Completion Failed")
          ).prettyPrint)
        }
      }
    }

  }
}
