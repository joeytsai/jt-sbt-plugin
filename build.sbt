
lazy val plugin = (project in file("."))
  .enablePlugins(JtSbtPlugin)
  .settings(
    sbtPlugin := true,
    name := "jt-sbt-plugin",
    // Must use Scala 2.10 for sbt 0.13
    // http://stackoverflow.com/questions/23282469/does-sbt-build-against-scala-2-11
    scalaVersion := "2.10.6"
  )

