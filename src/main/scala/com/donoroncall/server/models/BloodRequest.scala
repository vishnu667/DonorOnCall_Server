package com.donoroncall.server.models

import spray.json.{JsNumber, JsString, JsObject}

/**
  * Created by vishnu on 14/4/16.
  */
class BloodRequest(
                    createdUserId: Long,
                    contactNumber: String,
                    hospitalName: String,
                    hospitalAddress: String,
                    patientName: String,
                    requiredUnits: Int,
                    promisedUnits: Int=0,
                    fulfilledUnits: Int = 0,
                    requiredWithin: Long,
                    bloodGroup: String,
                    requestId: Long = 0,
                    purpose: String = "",
                    status: Int = 0, //-1 rejected, 0 not approved, 1 approved, 2 Complete
                    comment: String = "",
                    lat: Double = 0.0,
                    lon: Double = 0.0
                  ) {

  def toJson: JsObject = JsObject(
    "contactNumber" -> JsString(contactNumber),
    "hospitalName" -> JsString(hospitalName),
    "hospitalAddress" -> JsString(hospitalAddress),
    "patientName" -> JsString(patientName),
    "comment" -> JsString(comment),
    "bloodGroup" -> JsString(bloodGroup),
    "purpose" -> JsString(purpose),
    "createdUserId" -> JsNumber(createdUserId),
    "requiredUnits" -> JsNumber(requiredUnits),
    "requiredWithin" -> JsNumber(requiredWithin),
    "requestId" -> JsNumber(requestId),
    "status" -> JsNumber(status),
    "lat" -> JsNumber(lat),
    "lon" -> JsNumber(lon)
  )
}
