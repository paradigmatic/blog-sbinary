organization := "org.streum"

name := "blog-sbinary"

version := "0.0.1"

scalaVersion := "2.9.2"

crossScalaVersions := Seq("2.9.0-1", "2.9.1", "2.9.2" )

libraryDependencies += "org.scalatest" %% "scalatest" % "1.7.2" % "test"

libraryDependencies += "org.scala-tools.sbinary" % "sbinary_2.9.0" % "0.4.0"

//libraryDependencies += "com.typesafe.akka" % "akka-actor" % "2.0.1"

scalacOptions += "-deprecation"

scalaSource in Compile <<= baseDirectory(_ / "src")

scalaSource in Test <<= baseDirectory(_ / "test")

