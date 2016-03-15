package com.donoroncall.server.rest.controllers.authentication

import java.security.MessageDigest
import javax.print.attribute.standard.RequestingUserName

import com.donoroncall.server.BootStrapServer.mysqlClient
import com.google.inject.Inject
import scala.math._

/**
 * Created by anmol on 11/3/16.
 */
class EditProfileController @Inject()(sessionHandler: SessionHandler) {




  def getBloodGroup(userName: String): String = {

    val query = "SELECT bloodGroup from users where username='" + userName + "'"


    val resultSet = mysqlClient.getResultSet(query)
    val bloodGroup = resultSet.getString(1)

    return bloodGroup

  }

  def getName(userName: String): String = {

    val query = "SELECT name from users where username='" + userName + "'"


    val resultSet = mysqlClient.getResultSet(query)
    val name = resultSet.getString(1)

    return name

  }
  def getDob(userName: String): String = {

    val query = "SELECT dob from users where username='" + userName + "'"


    val resultSet = mysqlClient.getResultSet(query)
    val dob = resultSet.getString(1)

    return dob

  }


  def getEmail(userName: String): String = {

    val query = "SELECT email from users where username='" + userName + "'"


    val resultSet = mysqlClient.getResultSet(query)
    val email = resultSet.getString(1)

    return email

  }
  def getPhoneNo(userName: String): String = {

    val query = "SELECT phoneNo from users where username='" + userName + "'"


    val resultSet = mysqlClient.getResultSet(query)
    val phoneNo = resultSet.getString(1)

    return phoneNo

  }
  def updateProfile(userName: String, name: String, bloodGroup: String, dob:String): Boolean = {

    val query = "UPDATE users SET name = '"+ name +"', dob = '"+dob+"', bloodGroup = '"+bloodGroup+"'  WHERE username='" + userName + "'"
    mysqlClient.getResultSet(query)

  }





  def hash(text: String): String = {
    val sha256: MessageDigest = MessageDigest.getInstance("SHA-256")
    sha256.update(text.getBytes("UTF-8"))
    val digest = sha256.digest()
    String.format("%064x", new java.math.BigInteger(1, digest))
  }
}