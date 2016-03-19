package com.github.jt.sbt

import sbt._
import Keys._

object JtSbtPlugin extends AutoPlugin {

  override def projectSettings = Defaults.coreDefaultSettings ++ jtSettings

  private lazy val jtSettings = Seq(
    organization := "com.github.jt",
    scalaVersion := "2.11.7"
  )

}