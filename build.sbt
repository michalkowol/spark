import sbtassembly.PathList

name := "Spark Hackaton App"
version in ThisBuild := "1.0"
scalaVersion in ThisBuild := "2.11.5"

incOptions := incOptions.value.withNameHashing(true)
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

org.scalastyle.sbt.ScalastylePlugin.Settings
defaultScalariformSettings

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.2"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2"
libraryDependencies += "org.apache.spark" %% "spark-core" % "1.2.0"
libraryDependencies += "net.sf.extjwnl" % "extjwnl" % "1.8.0"
libraryDependencies += "net.sf.extjwnl" % "extjwnl-data-wn31" % "1.2"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.3" % "test"
libraryDependencies += "org.mockito" % "mockito-all" % "1.10.19" % "test"

assemblyJarName in assembly := "spark-hackaton-app.jar"
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "mailcap") => MergeStrategy.first
  case PathList("META-INF", "mimetypes.default") => MergeStrategy.first
  case "plugin.properties" => MergeStrategy.concat
  case x if x.endsWith(".class") => MergeStrategy.first
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}