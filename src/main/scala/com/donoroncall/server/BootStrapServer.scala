package com.donoroncall.server

import com.donoroncall.server.connectors.MysqlClient
import com.donoroncall.server.di.ServerDiModule
import com.donoroncall.server.rest.ServerInterface
import com.google.inject.Guice
import com.typesafe.config.Config
import org.slf4j.{LoggerFactory, Logger}
import com.donoroncall.server.utils.InitializationUtils.getConfiguration

/**
  * Created by vishnu on 20/1/16.
  */
object BootStrapServer extends App {
  println("Server Initializing")

  private val LOG: Logger = LoggerFactory.getLogger(BootStrapServer.getClass)

  val config: Config = getConfiguration

  val injector = Guice.createInjector(new ServerDiModule(config))

  val serverInterface = injector.getInstance(classOf[ServerInterface])

  serverInterface.startServer

  val mysqlClient = injector.getInstance(classOf[MysqlClient])
}
