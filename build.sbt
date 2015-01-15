name := "project"
version in ThisBuild := "1.0"
scalaVersion in ThisBuild := "2.10.5"

incOptions := incOptions.value.withNameHashing(true)
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

com.github.retronym.SbtOneJar.oneJarSettings
org.scalastyle.sbt.ScalastylePlugin.Settings
defaultScalariformSettings
jacoco.settings

libraryDependencies += "com.google.guava" % "guava" % "18.0"
libraryDependencies += "com.google.code.findbugs" % "jsr305" % "3.0.0"
libraryDependencies += "org.scaldi" %% "scaldi" % "0.4"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.2"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.3" % "test"
libraryDependencies += "org.mockito" % "mockito-all" % "1.10.19" % "test"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.2.0"
