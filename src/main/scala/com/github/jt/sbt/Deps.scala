package com.github.jt.sbt

import sbt._

/**
 * Dependencies which work together.
 */
object Deps {
  object Core {
    val jodaTime = "joda-time" % "joda-time" % "2.9.1"
    val jodaConvert = "org.joda" % "joda-convert" % "1.8.1"
  }
  object Json {
    private val version = "3.3.0"
    val json4s    = "org.json4s" %% "json4s-jackson" % version
    val json4sExt = "org.json4s" %% "json4s-ext" % version exclude(
      "joda-time", "joda-time") exclude("org.joda", "joda-convert")
  }

  object Scalatra {
    private val version = "2.4.0"
    private def module(name: String) = "org.scalatra" %% name % version
    val scalatra  = module("scalatra")
    val json      = module("scalatra-json")
    val swagger   = module("scalatra-swagger") exclude(
      "joda-time", "joda-time") exclude("org.joda", "joda-convert")
    val scalatest = module("scalatra-scalatest") % "it;test"

    val servletApi = "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided"
  }

  val scalatraDeps = Seq(
    Scalatra.scalatra,
    Scalatra.json,
    Scalatra.swagger,
    Scalatra.scalatest,
    Scalatra.servletApi,
    Json.json4s,
    Json.json4sExt,
    Core.jodaConvert,
    Core.jodaTime
  )
}
