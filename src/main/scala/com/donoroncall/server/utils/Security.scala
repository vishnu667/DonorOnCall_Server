package com.donoroncall.server.utils

import java.security.MessageDigest

/**
  * Created by vishnu on 30/3/16.
  */
object Security {

  def hash(text: String): String = {
    val sha256: MessageDigest = MessageDigest.getInstance("SHA-256")
    sha256.update(text.getBytes("UTF-8"))
    val digest = sha256.digest()
    String.format("%064x", new java.math.BigInteger(1, digest))
  }
}
