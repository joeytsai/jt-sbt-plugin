# jt-sbt-plugin

As sbt is recursive (of course) this plugin is very productive for working with multiple Scala projects.

## My take on SBT and Scala

SBT is misnamed.  Nobody thinks it's simple.  It should be named the Scala Build Tool; in my opinion, it's a good example of Scala and its philosophy.
* of course, it's written in Scala
* they made their own DSL (since Scala is the "Scalable Language")
* complicated
* powerful


## Features

1. sbt script from finagle
  * https://github.com/twitter/finagle/blob/develop/sbt
2. update sbt to latest 0.13.11
3. Per Jetbrains, including .idea
   https://www.jetbrains.com/help/idea/2016.1/managing-projects-under-version-control.html
4. Self-Plugin
  * https://github.com/sbt/sbt-release/blob/master/project/plugins.sbt
5. Scala 2.11 and Java 8 compiler settings
6. Support for sbt-git
  * https://github.com/sbt/sbt-git
  * Using git-describe, so users must git tag v0.0.1
7. JtRestPlugin for REST
  * Scalatra 2.3, Json 3.2
  * Superset of JtSbtPlugin
8. JtRestPlugin: use TomcatPlugin
  * https://github.com/earldouglas/xsbt-web-plugin
9. JtRestPlugin: add browse task from scalatra-sbt
  * https://github.com/scalatra/scalatra-sbt/blob/master/src/main/scala/org/scalatra/sbt/ScalatraPlugin.scala
10. JtRestPlugin: add tomcatDebug command
11. Add sbt-buildinfo plugin
  * https://github.com/sbt/sbt-buildinfo