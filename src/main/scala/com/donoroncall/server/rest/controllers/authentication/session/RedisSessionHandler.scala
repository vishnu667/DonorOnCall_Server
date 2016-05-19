package com.donoroncall.server.rest.controllers.authentication.session

import com.donoroncall.server.connectors.RedisConnector
import com.donoroncall.server.utils.TokenGenerator
import com.google.inject.Inject
import org.slf4j.{LoggerFactory, Logger}
import spray.json.{JsString, JsObject}

/**
  * Created by vishnu on 18/4/16.
  */
class RedisSessionHandler @Inject()(redisConnector: RedisConnector) extends SessionHandler {

  private val LOG: Logger = LoggerFactory.getLogger(this.getClass)

  private val redis = redisConnector.getClient("session")

  private val SESSION_TTL_SECONDS: Int = 24 * 60 * 60

  override def getUserIdForSession(sessionToken: String): Long = {
    LOG.debug("gettingUserFor Session " + sessionToken)
    try {
      val sessions = redis.get(sessionToken)
      if (sessions.isEmpty)
        0
      else {
        redis.expire(sessionToken, SESSION_TTL_SECONDS)
        sessions.head.toLong
      }
    } catch {
      case e: Exception => LOG.debug("Error While Fetching Session : " + sessionToken, e)
        0
    }
  }

  override def clearAllSessions: Boolean = {
    try {
      redis.flushdb
      true
    } catch {
      case e: Exception => LOG.debug("Error While Flushing the Session", e)
        false
    }
  }

  override def createSessionTokenForUser(userId: Long): String = {
    try {
      val token = TokenGenerator.generateSHAToken(userId.toString)
      redis.set(token, userId)
      redis.expire(token, SESSION_TTL_SECONDS)
      token
    } catch {
      case e: Exception => LOG.debug("Error While Creating the Session ", e)
        null
    }
  }

  override def getAllSessions: JsObject = try {
    val t: Map[String, String] = redis.hgetall() match {
      case Some(s) => s
      case _ => Map()
    }
    JsObject(t.map(i => i._1 -> JsString(i._2)))
  } catch {
    case e: Exception => LOG.debug("Error While Getting the Session Values", e)
      JsObject()
  }

  override def clearSessionToken(token: String): Unit = {
    try {
      redis.del(token)
    } catch {
      case e: Exception => LOG.debug("Error While Deleting the Session " + token, e)
    }

  }
}
