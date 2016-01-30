package com.donoroncall.server.rest.controllers.authentication

import com.donoroncall.server.models.User
import spray.json.JsObject

/**
  * Created by vishnu on 24/1/16.
  */
trait SessionHandler {

  def getUserIdForSession(sessionToken: String): Long

  def getUserForSession(sessionToken: String): User = {
    val userID = getUserIdForSession(sessionToken)
    if (userID != 0)
      User.getUser(userID)
    else
      null
  }

  def createSessionTokenForUser(userId: Long): String

  def clearSessionToken(token: String): Unit

  def clearAllSessions: Boolean

  def getAllSessions: JsObject
}
