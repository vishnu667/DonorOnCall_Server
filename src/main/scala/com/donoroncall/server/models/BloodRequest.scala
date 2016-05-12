package com.donoroncall.server.models

import java.sql.ResultSet

import com.donoroncall.server.BootStrapServer._
import com.donoroncall.server.utils.SqlUtils
import org.slf4j.{LoggerFactory, Logger}
import spray.json.{JsNumber, JsString, JsObject}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by vishnu on 14/4/16.
  */
class BloodRequest(
                    val createdUserId: Long,
                    val contactNumber: String,
                    val hospitalName: String,
                    val hospitalAddress: String,
                    val patientName: String,
                    val requiredUnits: Int,
                    val promisedUnits: Int = 0,
                    val fulfilledUnits: Int = 0,
                    val requiredWithin: Long,
                    val bloodGroup: String,
                    val requestId: Long = 0,
                    val purpose: String = "",
                    val status: Int = 0, //-1 rejected, 0 not
                    val comment: String = "",
                    val lat: Double = 0.0,
                    val lon: Double = 0.0
                  ) {

  def toJson: JsObject = JsObject(
    "contactNumber" -> JsString(contactNumber),
    "hospitalName" -> JsString(hospitalName),
    "hospitalAddress" -> JsString(hospitalAddress),
    "patientName" -> JsString(patientName),
    "bloodGroup" -> JsString(bloodGroup),
    "purpose" -> JsString(purpose),
    "comment" -> JsString(comment),
    "createdUserId" -> JsNumber(createdUserId),
    "requiredUnits" -> JsNumber(requiredUnits),
    "promisedUnits" -> JsNumber(promisedUnits),
    "fulfilledUnits" -> JsNumber(fulfilledUnits),
    "requiredWithin" -> JsNumber(requiredWithin),
    "requestId" -> JsNumber(requestId),
    "status" -> JsNumber(status),
    "lat" -> JsNumber(lat),
    "lon" -> JsNumber(lon)
  )
}

object BloodRequest {

  private val LOG: Logger = LoggerFactory.getLogger(this.getClass)

  def updateDb(bloodRequest: BloodRequest) = {

  }

  /**
    *
    * @param requestJson
    *               {
    "contactNumber": "13245645",
    "hospitalName": "hospital name",
    "hospitalAddress": " hospital address1, Hospital address 2",
    "patientName": "Patient Name",
    "bloodGroup": "0+ve",
    "purpose": "reason for blood Request will be displayed to the donors",
    "comment": "",
    "requiredUnits": 3,
    "lat": 0.0,
    "lon": 0.0,
    "requiredWithin": 1462022163000
  }
    * @param userId The users Id
    * @return
    */
  def registerRequest(requestJson: JsObject, userId: Long): (BloodRequest, Array[String]) = {
    var bloodRequest: BloodRequest = null
    val messages: scala.collection.mutable.ArrayBuffer[String] = ArrayBuffer.empty[String]
    try {
      val contactNumber = requestJson.getFields("contactNumber").head.asInstanceOf[JsString].value
      val hospitalName = requestJson.getFields("hospitalName").head.asInstanceOf[JsString].value
      val hospitalAddress = requestJson.getFields("hospitalAddress").head.asInstanceOf[JsString].value
      val patientName = requestJson.getFields("patientName").head.asInstanceOf[JsString].value
      val bloodGroup = requestJson.getFields("bloodGroup").head.asInstanceOf[JsString].value
      val purpose = requestJson.getFields("purpose").head.asInstanceOf[JsString].value
      val comment = requestJson.getFields("comment").head.asInstanceOf[JsString].value

      val requiredUnits = requestJson.getFields("requiredUnits").head.asInstanceOf[JsNumber].value.toInt

      val lat = requestJson.getFields("lat").head.asInstanceOf[JsNumber].value.toDouble
      val lon = requestJson.getFields("lon").head.asInstanceOf[JsNumber].value.toDouble

      val requiredWithin = requestJson.getFields("requiredWithin").head.asInstanceOf[JsNumber].value.toLong


      val requestId = SqlUtils.insert("blood_request", Map(
        "userId" -> userId,
        "contact_number" -> contactNumber,
        "hospital_name" -> hospitalName,
        "hospital_address" -> hospitalAddress,
        "patient_name" -> patientName,
        "required_units" -> requiredUnits,
        "promised_units" -> 0,
        "fulfilled_units" -> 0,
        "required_within" -> requiredWithin,
        "blood_group" -> bloodGroup,
        "purpose" -> purpose,
        "status" -> 0,
        "comment" -> comment,
        "lat" -> lat,
        "lon" -> lon
      ))

      bloodRequest = getBloodRequest(requestId)
      messages += "Blood Request Created Successfully with Id " + requestId

    } catch {
      case u: UnsupportedOperationException => {
        messages += "Invalid Request Json"
        LOG.debug("Error While Creating user", u)
      }
      case e: Exception => {
        messages += e.getLocalizedMessage
        LOG.debug("Error While Creating user", e)
      }
    }
    (bloodRequest, messages.toArray)
  }

  def getBloodRequest(requestId: Long): BloodRequest = try {
    val resultSet = mysqlClient.getResultSet("SELECT * from blood_request where requestId=" + requestId)

    if (resultSet.next()) {
      getBloodRequestFromResultSet(resultSet)
    } else null
  } catch {
    case e: Exception => LOG.debug("exception in Getting User " + requestId, e)
      null
  }

  def getBloodRequestFromResultSet(resultSet: ResultSet): BloodRequest = {
    val contactNumber = resultSet.getString("contact_number")
    val hospitalName = resultSet.getString("hospital_name")
    val hospitalAddress = resultSet.getString("hospital_address")
    val patientName = resultSet.getString("patient_name")
    val bloodGroup = resultSet.getString("blood_group")
    val purpose = resultSet.getString("purpose")
    val comment = resultSet.getString("comment")

    val lat = resultSet.getDouble("lat")
    val lon = resultSet.getDouble("lon")

    val status = resultSet.getInt("status")
    val requiredUnits = resultSet.getInt("required_units")
    val promisedUnits = resultSet.getInt("promised_units")
    val fulfilledUnits = resultSet.getInt("fulfilled_units")

    val createdUserId = resultSet.getLong("userId")
    val requestId = resultSet.getLong("requestId")
    val requiredWithin = resultSet.getLong("required_within")

    new BloodRequest(
      createdUserId = createdUserId,
      contactNumber = contactNumber,
      hospitalName = hospitalName,
      hospitalAddress = hospitalAddress,
      patientName = patientName,
      requiredUnits = requiredUnits,
      promisedUnits = promisedUnits,
      fulfilledUnits = fulfilledUnits,
      requiredWithin = requiredWithin,
      bloodGroup = bloodGroup,
      requestId = requestId,
      purpose = purpose,
      status = status,
      comment = comment,
      lat = lat,
      lon = lon
    )

  }
}
