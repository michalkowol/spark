name := "Spark Hackaton App"
version in ThisBuild := "1.0"
scalaVersion in ThisBuild := "2.10.4"

incOptions := incOptions.value.withNameHashing(true)
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

org.scalastyle.sbt.ScalastylePlugin.Settings
defaultScalariformSettings

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.2"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2"

//libraryDependencies += "org.apache.spark" %% "spark-core" % "1.2.0"