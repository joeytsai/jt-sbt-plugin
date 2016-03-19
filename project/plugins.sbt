// From: https://github.com/sbt/sbt-release/blob/master/project/plugins.sbt
// This project is its own plugin :)
unmanagedSourceDirectories in Compile += baseDirectory.value.getParentFile / "src" / "main" / "scala"