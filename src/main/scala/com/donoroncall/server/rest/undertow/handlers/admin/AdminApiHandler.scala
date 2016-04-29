package com.donoroncall.server.rest.undertow.handlers.admin

import com.donoroncall.server.rest.controllers.authentication.session.SessionHandler
import com.google.inject.Inject
import io.undertow.server.{HttpServerExchange, HttpHandler}
import org.apache.commons.io.IOUtils
import org.slf4j.{LoggerFactory, Logger}
import spray.json._

/**
  * Created by vishnu on 25/1/16.
  */
class AdminApiHandler @Inject()(sessionHandler: SessionHandler) extends HttpHandler {
  private val LOG: Logger = LoggerFactory.getLogger(this.getClass)

  override def handleRequest(exchange: HttpServerExchange): Unit = {
    if (exchange.isInIoThread) {
      exchange.dispatch(this)
    } else {
      try {
        exchange.startBlocking()
        val request = new String(IOUtils.toByteArray(exchange.getInputStream))

        val requestJson = request.parseJson.asJsObject

        val token = requestJson.getFields("token").head.asInstanceOf[JsString].value

        val userId = sessionHandler.getUserIdForSession(token)

      } catch {
        case e: Exception => {
          LOG.debug("exception", e)
          exchange.getResponseSender.send("Exception in server")
        }
      }
    }
  }
}
