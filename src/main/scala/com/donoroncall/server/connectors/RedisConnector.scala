package com.donoroncall.server.connectors

import com.google.inject.Inject
import com.redis.RedisClient
import com.typesafe.config.Config
import scala.collection.JavaConversions._

/**
  * Created by vishnu on 18/4/16.
  */
class RedisConnector @Inject()(config: Config) {

  private val clientsMap = config.getConfigList("server.redis.databases").map(f =>
    f.getString("name") -> new RedisClient(
      config.getString("server.redis.host"),
      config.getString("server.redis.port").toInt,
      f.getInt("number"))
  ).toMap

  def getClient(database: String): RedisClient = clientsMap.getOrElse(database, null)
}

object RedisConnector {



}
