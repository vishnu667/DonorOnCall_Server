package com.donoroncall.server.rest.controllers.authentication

import java.security.MessageDigest

import com.donoroncall.server.BootStrapServer.mysqlClient
import com.google.inject.Inject

/**
  * Created by vishnu on 20/1/16.
  */
class AuthenticationController @Inject()(sessionHandler: SessionHandler) {

  def login(username: String, password: String): String = {
    val query = "SELECT passwordHash,userId from users where username='" + username + "'"
    val resultSet = mysqlClient.getResultSet(query)
    if (resultSet.next()) {
      val passwordHash = resultSet.getString(1)
      if (passwordHash == hash(password)) {
        sessionHandler.createSessionTokenForUser(resultSet.getLong(2))
      } else ""
    } else ""
  }

  def addNewUser(userName: String, password: String, email: String): Boolean = {
    //TODO add more validations if user exists etc
    val insertQuery = "INSERT INTO users (userName,passwordHash,email) VALUES ('" + userName + "','" + hash(password) + "','" + email + "')"
    mysqlClient.executeQuery(insertQuery)
  }

  def hash(text: String): String = {
    val sha256: MessageDigest = MessageDigest.getInstance("SHA-256")
    sha256.update(text.getBytes("UTF-8"))
    val digest = sha256.digest()
    String.format("%064x", new java.math.BigInteger(1, digest))
  }
}
