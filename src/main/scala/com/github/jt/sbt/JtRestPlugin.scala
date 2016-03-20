package com.github.jt.sbt

import java.net.URI

import com.earldouglas.xwp.ContainerPlugin.autoImport.containerPort
import com.earldouglas.xwp.TomcatPlugin
import com.earldouglas.xwp.TomcatPlugin.autoImport.Tomcat
import sbt.Keys._
import sbt._

/**
 * Add Scalatra to library dependencies
 * Load the TomcatPlugin
 * Add a browse task
 * Add tomcatDebug command
 */
object JtRestPlugin extends AutoPlugin {

  override def requires = JtSbtPlugin && TomcatPlugin

  object autoImport {
    val browse = taskKey[Unit]("Open a web browser to localhost:port")
  }

  import autoImport._

  // From: https://github.com/scalatra/scalatra-sbt/blob/master/src/main/scala/org/scalatra/sbt/ScalatraPlugin.scala
  lazy val browseTask = browse := {
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

  private lazy val debugOpts = Seq(
    "-Xdebug",
    "-Xrunjdwp:server=y,transport=dt_socket,address=5005,suspend=n"
  )

  def tomcatDebug = Command.command("tomcatDebug") { state =>
    import com.earldouglas.xwp.ContainerPlugin.start
    val state2 =
      Project.extract(state).append(
        Seq(javaOptions in Tomcat ++= debugOpts),
        state
      )
    Project.extract(state2).runTask(start in Tomcat, state2)
    state2
  }

  override val projectSettings = restPluginKeys ++ Seq(
    libraryDependencies ++= Deps.restDeps,
    commands += tomcatDebug
  )

  lazy val restPluginKeys = Seq(browseTask)
}
