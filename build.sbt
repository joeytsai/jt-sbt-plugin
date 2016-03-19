
lazy val plugin = (project in file("."))
  .settings(
    sbtPlugin := true,
    name := "jt-sbt-plugin"
  )

