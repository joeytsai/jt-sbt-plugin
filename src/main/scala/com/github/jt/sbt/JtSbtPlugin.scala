package com.github.jt.sbt

import sbt._
import Keys._

/**
 * Common settings for Scala projects.
 *
 * Leaving trigger = noTrigger, so users must enablePlugin(JtSbtPlugin)
 */
object JtSbtPlugin extends AutoPlugin {

  //override def requires = plugins.JvmPlugin && GitBranchPrompt && GitVersioning
  override def projectSettings = Defaults.coreDefaultSettings ++ jtSettings

  private lazy val jtSettings = Seq(
    organization := "com.github.jt",
    scalaVersion := "2.11.8",
    // Scala 2.11 incremental compilation
    incOptions := incOptions.value.withNameHashing(true),
    // Don't publish javadoc or sources
    publishArtifact in (Compile, packageDoc) := false,
    publishArtifact in (Compile, packageSrc) := false,

    scalacOptions := Seq(
      "-encoding", "UTF-8",
      "-deprecation",
      "-unchecked",
      "-feature",
      "-target:jvm-1.8",
      "-Xlint",
      "-Xfatal-warnings"
    ),

    javacOptions := Seq(
      "-deprecation",
      "-target", "1.8",
      "-Xlint",
      "-Werror"
    )
  )

}