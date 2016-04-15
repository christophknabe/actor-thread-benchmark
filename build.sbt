name := "actor-thread-benchmark"

version := "0.3-SNAPSHOT"

scalaVersion := "2.11.7"

lazy val akkaVersion = "2.4.3"

libraryDependencies ++= Seq(
  //"com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion
)

//Download artifact sources. See https://github.com/typesafehub/sbteclipse/wiki/Using-sbteclipse
EclipseKeys.withSource := true

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")
