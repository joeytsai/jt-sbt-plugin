
lazy val plugin = (project in file("."))
  .enablePlugins(JtSbtPlugin)
  .settings(
    sbtPlugin := true,
    name := "jt-sbt-plugin",
    scalaVersion := "2.10.6"
  )

