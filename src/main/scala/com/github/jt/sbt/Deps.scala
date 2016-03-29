package com.github.jt.sbt

import sbt._

/**
 * Dependencies which work together.
 */
object Deps {
  object Log {
    val slf4j = "org.slf4j" % "slf4j-api" % "1.7.19"
    val grizzled = "org.clapper" %% "grizzled-slf4j" % "1.0.2" exclude("org.slf4j", "slf4j-api")
    val logback = "ch.qos.logback" % "logback-classic" % "1.1.6"

  }
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
    val scalatra  = module("scalatra") exclude("org.slf4j", "slf4j-api")
    val json      = module("scalatra-json")
    val swagger   = module("scalatra-swagger") exclude(
      "joda-time", "joda-time") exclude("org.joda", "joda-convert")
    val scalatest = module("scalatra-scalatest") % "it;test"

    val servletApi = "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided"
  }
  object Database {
    val mysql = "mysql" % "mysql-connector-java" % "5.1.38"
    val pool = "com.zaxxer" % "HikariCP" % "2.4.5"
    val jdbc = "org.scalikejdbc" %% "scalikejdbc" % "2.3.5"
  }

  val config = "com.typesafe" % "config" % "1.3.0"

  val restDeps = Seq(
    Log.slf4j,
    Log.grizzled,
    Log.logback,
    Core.jodaConvert,
    Core.jodaTime,
    Json.json4s,
    Json.json4sExt,
    Scalatra.scalatra,
    Scalatra.json,
    Scalatra.swagger,
    Scalatra.scalatest,
    Scalatra.servletApi
  )
}
