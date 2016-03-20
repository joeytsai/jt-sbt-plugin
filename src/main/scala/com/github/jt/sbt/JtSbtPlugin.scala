package com.github.jt.sbt

import com.typesafe.sbt.SbtGit.git
import com.typesafe.sbt.{GitBranchPrompt, GitVersioning}
import sbt.Keys._
import sbt._

/**
 * Common settings for Scala projects.
 *
 * Leaving trigger = noTrigger, so users must enablePlugin(JtSbtPlugin)
 * This plugin includes the sbt-git plugins
 */
object JtSbtPlugin extends AutoPlugin {

  override def requires = plugins.JvmPlugin && GitBranchPrompt && GitVersioning
  override val projectConfigurations = Seq(IntegrationTest)
  override def projectSettings = Defaults.coreDefaultSettings ++ Defaults.itSettings ++ commonSettings ++ pluginSettings

  private lazy val commonSettings = Seq(
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

  private lazy val pluginSettings = Seq(
    // plugin: sbt-git
    // remember to git tag v0.0.1
    git.useGitDescribe := true
  )

}