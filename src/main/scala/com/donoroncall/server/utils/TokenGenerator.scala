package com.donoroncall.server.utils

import java.security.SecureRandom
import java.security.MessageDigest

/**
  * Created by vishnu on 25/1/16.
  */
object TokenGenerator {

  private val TOKEN_LENGTH = 45

  private val TOKEN_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"

  private val charLen = TOKEN_CHARS.length()

  private val secureRandom = new SecureRandom()

  private def toHex(bytes: Array[Byte]): String = bytes.map("%02x".format(_)).mkString("")

  private def sha(s: String): String = toHex(MessageDigest.getInstance("SHA-256").digest(s.getBytes("UTF-8")))

  private def md5(s: String): String = toHex(MessageDigest.getInstance("MD5").digest(s.getBytes("UTF-8")))

  def generateToken(tokenLength: Int): String = new String((0 until tokenLength).map(i => TOKEN_CHARS(secureRandom.nextInt(charLen))).toArray)

  def generateMD5Token(tokenPrefix: String): String = md5(tokenPrefix + System.nanoTime() + generateToken(TOKEN_LENGTH))

  def generateSHAToken(tokenPrefix: String): String = sha(tokenPrefix + System.nanoTime() + generateToken(TOKEN_LENGTH))

}

