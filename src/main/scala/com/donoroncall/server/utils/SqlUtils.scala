package com.donoroncall.server.utils

import java.sql.PreparedStatement

import org.slf4j.{LoggerFactory, Logger}
import spray.json.{JsArray, JsObject}

import scala.collection.mutable.ArrayBuffer
import com.donoroncall.server.BootStrapServer.mysqlClient

/**
  * Created by vishnu on 29/3/16.
  */
object SqlUtils {

  private val LOG: Logger = LoggerFactory.getLogger(this.getClass)

  private val autoIncValuesForTable: Map[String, Array[String]] = Map("users" -> Array("userId"),
                                                                       "deviceId" -> Array("registered_devices"))

  def insert(tableName: String, elements: Map[String, Any]): Long = {
    try {
      val colNames: ArrayBuffer[String] = ArrayBuffer()
      val values: ArrayBuffer[Any] = ArrayBuffer()
      elements.foreach(i => {
        colNames += i._1
        values += i._2
      })

      val insertQuery = "INSERT INTO " + tableName + " (" + colNames.mkString(",") + ") VALUES (" + colNames.indices.map(i => "?").mkString(",") + ")"

      val returnColumns: Array[String] = autoIncValuesForTable.getOrElse(tableName, Array())
      val preparedStatement: PreparedStatement = mysqlClient.getConnection.prepareStatement(insertQuery, returnColumns)

      values.zipWithIndex.foreach(i => addToPreparedStatement(i._1, i._2 + 1, preparedStatement))

      var generatedId: Long = 0l
      try {
        LOG.debug("insert Query : " + preparedStatement.toString)
        preparedStatement.executeUpdate()
        if (returnColumns.nonEmpty) {
          val gkSet = preparedStatement.getGeneratedKeys
          if (gkSet.next()) {
            generatedId = gkSet.getLong(1)
          }
        }
      }
      finally preparedStatement.close()

      generatedId
    } catch {
      case e: Exception => e.printStackTrace()
        0l
    }
  }

  private def addToPreparedStatement(value: Any, index: Int, preparedStatement: PreparedStatement) = {
    value match {
      case _: Long => preparedStatement.setLong(index, value.asInstanceOf[Long])
      case _: Int => preparedStatement.setInt(index, value.asInstanceOf[Int])
      case _: Double => preparedStatement.setDouble(index, value.asInstanceOf[Double])
      case _: String => preparedStatement.setString(index, value.asInstanceOf[String])
      case _: JsObject => preparedStatement.setString(index, value.asInstanceOf[JsObject].toString())
      case _: JsArray => preparedStatement.setString(index, value.asInstanceOf[JsArray].toString())
      case _: Object => preparedStatement.setObject(index, value)
      case _ => preparedStatement.setString(index, value.toString)
    }
  }
}
