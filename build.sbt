
lazy val plugin = (project in file("."))
  .enablePlugins(JtSbtPlugin)
  .settings(
    sbtPlugin := true,
    name := "jt-sbt-plugin",
    // Must use Scala 2.10 for sbt 0.13
    // http://stackoverflow.com/questions/23282469/does-sbt-build-against-scala-2-11
    scalaVersion := "2.10.6",
    // JDK8 support was not added until Scala 2.11, so need to specify scalacOptions for 2.10
    scalacOptions := Seq(
      "-encoding", "UTF-8",
      "-deprecation",
      "-unchecked",
      "-feature",
      "-target:jvm-1.7",
      "-Xlint",
      "-Xfatal-warnings"
    ),
    publishLocal := {
      val cl = clearLocal.value
      publishLocal.value
    }
  )

lazy val clearLocal = Def.task {
  val log = streams.value.log
  log.info("Removing old versions of this plugin...")
  IO.delete(Path.userHome / ".ivy2" / "local" / "com.github.jt" / "jt-sbt-plugin")
  IO.delete(Path.userHome / ".ivy2" / "local" / "jt-sbt-plugin")
  log.info("... removal complete.")
}

// Keep plugins in sync with project/plugins.sbt
addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "0.8.5")
addSbtPlugin("com.earldouglas" % "xsbt-web-plugin" % "2.1.0")