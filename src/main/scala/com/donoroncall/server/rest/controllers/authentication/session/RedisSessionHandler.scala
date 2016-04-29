package com.donoroncall.server.rest.controllers.authentication.session

import com.redis.RedisClient
import spray.json.JsObject

/**
  * Created by vishnu on 18/4/16.
  */
class RedisSessionHandler extends SessionHandler {
  override def getUserIdForSession(sessionToken: String): Long = ???

  override def clearAllSessions: Boolean = ???

  override def createSessionTokenForUser(userId: Long): String = ???

  override def getAllSessions: JsObject = ???

  override def clearSessionToken(token: String): Unit = ???
}

object RedisSessionHandler {

}
