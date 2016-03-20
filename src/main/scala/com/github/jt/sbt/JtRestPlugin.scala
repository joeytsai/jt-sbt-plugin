package com.github.jt.sbt

import java.net.URI

import com.earldouglas.xwp.ContainerPlugin.autoImport.containerPort
import com.earldouglas.xwp.TomcatPlugin
import com.earldouglas.xwp.TomcatPlugin.autoImport.Tomcat
import com.github.jt.sbt.Deps._
import sbt.Keys._
import sbt._

/**
 * Created by joeyt on 3/19/16.
 */
object JtRestPlugin extends AutoPlugin {

  override def requires = JtSbtPlugin && TomcatPlugin

  object autoImport {
    val browse = taskKey[Unit]("Open a web browser to localhost:port")
  }

  import autoImport._

  // From: https://github.com/scalatra/scalatra-sbt/blob/master/src/main/scala/org/scalatra/sbt/ScalatraPlugin.scala
  val browseTask = browse := {
    val log = streams.value.log

    // read port for jetty, default to 8080
    val port = {
      val p = (containerPort in Tomcat).value
      if (p == -1) 8080 else p
    }

    val url = URI.create("http://localhost:%s" format port)
    try {
      log.info("Launching browser.")
      java.awt.Desktop.getDesktop.browse(url)
    } catch {
      case _: Throwable => {
        log.info(f"Could not open browser, sorry. Open manually to ${url.toASCIIString}")
      }
    }
  }

  override val projectSettings = restPluginKeys ++ Seq(
    libraryDependencies ++= Seq(
      Scalatra.scalatra,
      Scalatra.json,
      Scalatra.swagger,
      Scalatra.scalatest,
      Scalatra.servletApi,
      Json.json4s,
      Json.json4sExt
    )
  )

  lazy val restPluginKeys = Seq(browseTask)
}
