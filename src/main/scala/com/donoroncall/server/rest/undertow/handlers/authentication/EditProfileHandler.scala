package com.donoroncall.server.rest.undertow.handlers.authentication

import com.donoroncall.server.rest.controllers.authentication.EditProfileController
import com.google.inject.Inject
import io.undertow.server.{HttpHandler, HttpServerExchange}
import org.apache.commons.io.IOUtils
import spray.json._

/**
 * Created by anmol on 11/3/16.
 */
class EditProfileHandler @Inject()(editProfileController:EditProfileController ) extends HttpHandler {
  override def handleRequest(exchange: HttpServerExchange): Unit = {
    if (exchange.isInIoThread) {
      exchange.dispatch(this)
    } else {
      try {
        exchange.startBlocking()
        val request = new String(IOUtils.toByteArray(exchange.getInputStream))

        val requestJson = request.parseJson.asJsObject

        val userName = requestJson.getFields("userName").head.asInstanceOf[JsString].value


        val bloodGroup = editProfileController.getBloodGroup(userName)

        val dob = editProfileController.getDob(userName)

        val name = editProfileController.getName(userName)

        // val email = editProfileController.getEmail(userName)

        // val phoneNo = editProfileController.getPhoneNo(userName)

        if (userName != null) {
          // to verify if this is the correct way
          exchange.getResponseSender.send(JsObject(
            "status" -> JsString("ok"),
            "userName" -> JsString(userName),
            "bloodGroup" -> JsString(bloodGroup),
            "name" -> JsString(name),
            "dob" -> JsString(dob),



            "message" -> JsString("Registration successful")
          ).prettyPrint)

        } else {
          //TODO add logic for Failed Registration
          exchange.getResponseSender.send(JsObject(
            "status" -> JsString("failed"),
            "message" -> JsString("Could not load profile details")
          ).prettyPrint)
        }


      } catch {
        case e: Exception => {
          exchange.getResponseSender.send(JsObject(
            "status" -> JsString("failed"),
            "message" -> JsString("Could not load profile details")
          ).prettyPrint)
        }
      }
    }

  }
}
