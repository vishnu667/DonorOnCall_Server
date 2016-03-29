package com.donoroncall.server.models

import java.sql.ResultSet
import java.util.Date
import java.text.{SimpleDateFormat, DateFormat}
import com.donoroncall.server.BootStrapServer.mysqlClient
import com.donoroncall.server.utils.SqlUtils
import org.slf4j.{Logger, LoggerFactory}
import spray.json.{JsNumber, JsObject, JsString}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by vishnu on 30/1/16.
  */
class User(userId: Long,
           email: String,
           userName: String,
           name: String,
           zipCode: Int,
           dob: Date,
           bloodGroup: String,
           passwordHash: String,
           lat: Double,
           lon: Double,
           phone: String
          ) {
  private val LOG: Logger = LoggerFactory.getLogger(this.getClass)

  def toJson: JsObject = JsObject(
    "email" -> JsString(email),
    "userName" -> JsString(userName),
    "name" -> JsString(name),
    "bloodGroup" -> JsString(bloodGroup),
    "dob" -> JsString(User.dateToString(dob)),
    "zipCode" -> JsNumber(zipCode),
    "lat" -> JsNumber(lat),
    "lon" -> JsNumber(lon),
    "phone" -> JsString(phone)
  )
}

object User {
  private val LOG: Logger = LoggerFactory.getLogger(this.getClass)

  private val dateFormat: DateFormat = new SimpleDateFormat("yyyy-mm-dd")

  private def dateToString(date: Date): String = dateFormat.format(date)

  private def stringToDate(date: String): Date = dateFormat.parse(date)

  def getUser(userId: Long): User = {
    try {
      val resultSet = mysqlClient.getResultSet("SELECT * from users where userId=" + userId)

      if (resultSet.next()) {
        getUserFromResultSet(resultSet)
      } else null
    } catch {
      case e: Exception => LOG.debug("exception in Getting User " + userId, e)
        null
    }
  }

  /**
    *
    * @param requestJson
  {
                      "name": "vishnu",
                      "bloodGroup": "0+ve",
                      "latitude": 0.01,
                      "email": "vishnua2ABC",
                      "dob": "08-10-1991",
                      "confirmPassword": "qwe",
                      "longitude": 0.02,
                      "phoneNo": "12123",
                      "userName": "vishnua2ABC",
                      "password": "qwe"
                    }
    * @return
    */
  def registerUser(requestJson: JsObject): (User, Array[String]) = {
    var user: User = null
    val messages: scala.collection.mutable.ArrayBuffer[String] = ArrayBuffer.empty[String]
    try {
      val userName = requestJson.getFields("userName").head.asInstanceOf[JsString].value
      val name = requestJson.getFields("name").head.asInstanceOf[JsString].value
      val dob = requestJson.getFields("dob").head.asInstanceOf[JsString].value
      val bloodGroup = requestJson.getFields("bloodGroup").head.asInstanceOf[JsString].value
      val password = requestJson.getFields("password").head.asInstanceOf[JsString].value
      val confirmPassword = requestJson.getFields("confirmPassword").head.asInstanceOf[JsString].value
      val latitude = requestJson.getFields("latitude").head.asInstanceOf[JsNumber].value.toDouble
      val longitude = requestJson.getFields("longitude").head.asInstanceOf[JsNumber].value.toDouble
      val phoneNo = requestJson.getFields("phoneNo").head.asInstanceOf[JsString].value
      val email = requestJson.getFields("email").head.asInstanceOf[JsString].value

      val sanityCheck = mysqlClient.getResultSet("SELECT username,email from users where username='" + userName + "' || email='" + email + "'")
      if (!sanityCheck.next()) {

        if (password == confirmPassword) {
          val passwordHash = ""
          val userId = SqlUtils.insert("users", Map(
            "userName" -> userName,
            "password_hash" -> passwordHash,
            "name" -> name,
            "blood_group" -> bloodGroup,
            "dob" -> dob,
            "latitude" -> latitude,
            "longitude" -> longitude,
            "phoneNo" -> phoneNo,
            "email" -> email
          ))
          user = getUser(userId)
          messages += "User Created Sussfully with Id " + userId
        } else {
          messages += " Passwords Do not Match"
        }
      } else {
        if (sanityCheck.getString(1).equals(userName)) messages += "userName : " + userName + " Exists !"
        if (sanityCheck.getString(2).equals(email)) messages += "email " + email + " Exists !"
      }

    } catch {
      case e: Exception => {
        messages += e.getLocalizedMessage
        LOG.debug("Error While Creating user", e)
      }
    }
    (user, messages.toArray)
  }


  /**
    * @param resultSet Pass the resultSet from the users Table to extract the user Object
    * @return Returns the User Object from the given result Set
    */
  def getUserFromResultSet(resultSet: ResultSet): User = {
    val username = resultSet.getString("username")
    val password_hash = resultSet.getString("password_hash")
    val email = resultSet.getString("email")
    val phoneNo = resultSet.getString("phoneNo")

    val address_1 = resultSet.getString("address_1")
    val address_2 = resultSet.getString("address_2")
    val locality = resultSet.getString("locality")
    val city = resultSet.getString("city")
    val blood_group = resultSet.getString("blood_group")
    val account_status = resultSet.getString("account_status")
    val health_information = resultSet.getString("health_information")

    val dob = resultSet.getString("dob")
    val latitude = resultSet.getString("latitude")
    val longitude = resultSet.getString("longitude")

    val request_count = resultSet.getInt("request_count")
    val fulfilled_count = resultSet.getInt("fulfilled_count")
    val donation_count = resultSet.getInt("donation_count")
    val zipCode = resultSet.getInt("zipCode")

    val is_donor = resultSet.getBoolean("is_donor")
    val is_recipient = resultSet.getBoolean("is_recipient")
    val is_admin_approved = resultSet.getBoolean("is_admin_approved")

    val tuserId = resultSet.getLong("userId")

    new User(
      userId = tuserId,
      email = email,
      userName = username,
      name = username,
      zipCode = zipCode,
      dob = stringToDate(dob),
      bloodGroup = blood_group,
      passwordHash = password_hash,
      lat = latitude.toDouble,
      lon = longitude.toDouble,
      phone = phoneNo
    )
  }

}
