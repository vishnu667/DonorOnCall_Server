package com.donoroncall.server.rest

/**
  * Created by vishnu on 20/1/16.
  */
trait ServerInterface {

  var isServerActive: Boolean

  def startServer: Boolean

  def stopServer: Boolean


}
