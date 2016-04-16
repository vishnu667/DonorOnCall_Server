package com.donoroncall.server.models

import java.sql.ResultSet

import com.donoroncall.server.BootStrapServer._
import com.donoroncall.server.utils.SqlUtils
import org.slf4j.{LoggerFactory, Logger}
import spray.json.{JsNumber, JsObject}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by vishnu on 14/4/16.
  */

/**
  *
  * @param donationId The current Records Id 0 for insert
  * @param userId     The Donors UserId
  * @param requestId  The BloodRequest record id
  * @param status
  *                   -2 => Deleted Record
  *                   -1 => Request Completed
  *                   0 => Recipient Canceled
  *                   1 => Pending
  *                   2 => Donor Accepted
  *                   3 => Donor Canceled
  *                   4 => Successfully Completed
  */
class DonationRecord(
                      donationId: Long = 0,
                      userId: Long,
                      requestId: Long,
                      status: Int = 0
                    ) {

  def toJson: JsObject = JsObject(
    "donationId" -> JsNumber(donationId),
    "userId" -> JsNumber(userId),
    "requestId" -> JsNumber(requestId),
    "status" -> JsNumber(status)
  )

}

object DonationRecord {
  private val LOG: Logger = LoggerFactory.getLogger(this.getClass)

  def getDonationRecord(donationId: Long) = try {
    val resultSet = mysqlClient.getResultSet("SELECT * from donation_record where donationId=" + donationId)

    if (resultSet.next()) {
      getDonationRecordFromResultSet(resultSet)
    } else null
  } catch {
    case e: Exception => LOG.debug("exception in Getting Donation Record " + donationId, e)
      null
  }

  /**
    *
    * @param requestJson
    *                    {
                          "userId":1202,
                          "requestId":120,
                          "status":2,
                         }
    * @return
    */
  def registerDonationRecord(requestJson: JsObject): (DonationRecord, Array[String]) = {
    var donationRecord: DonationRecord = null
    val messages: scala.collection.mutable.ArrayBuffer[String] = ArrayBuffer.empty[String]
    try {
      val userId = requestJson.getFields("userId").head.asInstanceOf[JsNumber].value.toLong
      val requestId = requestJson.getFields("requestId").head.asInstanceOf[JsNumber].value.toLong
      val status = requestJson.getFields("status").head.asInstanceOf[JsNumber].value.toInt

      val donationId = SqlUtils.insert("donation_record", Map(
        "user_id" -> userId,
        "request_id" -> requestId,
        "status" -> status
      ))

      donationRecord = getDonationRecord(donationId)
      messages += "Donation Record Created Successfully with Id " + donationId
    } catch {
      case u: UnsupportedOperationException => {
        messages += "Invalid Request Json"
        LOG.debug("Error While Creating Donation Record", u)
      }
      case e: Exception => {
        messages += e.getLocalizedMessage
        LOG.debug("Error While Creating Donation Record", e)
      }
    }
    (donationRecord, messages.toArray)
  }

  def getDonationRecordFromResultSet(resultSet: ResultSet): DonationRecord = {
    val id = resultSet.getLong("donationId")
    val userId = resultSet.getLong("user_id")
    val requestId = resultSet.getLong("request_id")
    val status = resultSet.getInt("status")

    new DonationRecord(
      donationId = id,
      userId = userId,
      requestId = requestId,
      status = status
    )
  }

}