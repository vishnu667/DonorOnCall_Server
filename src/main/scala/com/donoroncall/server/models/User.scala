package com.donoroncall.server.models

import com.donoroncall.server.BootStrapServer.mysqlClient
import org.slf4j.{Logger, LoggerFactory}
import spray.json.{JsObject, JsString}

/**
  * Created by vishnu on 30/1/16.
  */
class User(userId: Long, email: String, userName: String) {
  private val LOG: Logger = LoggerFactory.getLogger(this.getClass)

  def toJson: JsObject = JsObject(
    "email" -> JsString(email),
    "userName" -> JsString(userName)
  )
}

object User {
  private val LOG: Logger = LoggerFactory.getLogger(this.getClass)

  def createUser(): User = {
    null
  }

  def getUser(userId: Long): User = {
    try {
      val resultSet = mysqlClient.getResultSet("SELECT email,userName from users where userId=" + userId)

      if (resultSet.next()) {

        val email = resultSet.getString(1)
        val userName = resultSet.getString(2)

        new User(userId, email, userName)

      } else
        null
    } catch {
      case e: Exception => LOG.debug("exception in Getting User " + userId, e)
        null
    }
  }
}
