package com.donoroncall.server.utils

import scala.io.Source
import spray.json._

/**
  * Created by vishnu on 12/4/16.
  */
object ZipCodeApi {


  /**
    * Using the Google geocoding api to fetch the Postal Code from the coordinates
    * Method Reference : http://stackoverflow.com/a/13599014/2104970
    * @param lat Latitude as Double
    * @param lng Longitude as Double
    * @return returns the respective ZipCode
    *         if any exceptions are caught returns 0 instead
    */
  def getZipCodeFrom(lat: Double, lng: Double) = {
    try {
      Source.fromURL("http://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + "," + lng + "&sensor=false").mkString.parseJson.asJsObject
        .getFields("results").head.asInstanceOf[JsArray].elements.filter(i => {
        val component = i.asJsObject
        component.getFields("types").head.asInstanceOf[JsArray].elements.map(
          _.asInstanceOf[JsString].value).contains("postal_code")
      }).head.asJsObject.getFields("address_components")
        .head.asInstanceOf[JsArray].elements.filter(i => {
        val component = i.asJsObject
        component.getFields("types").head.asInstanceOf[JsArray].elements.map(
          _.asInstanceOf[JsString].value).contains("postal_code")
      }).head.asJsObject.getFields("long_name").head.asInstanceOf[JsString].value
    } catch {
      case e: Exception => "0"
    }
  }
}
