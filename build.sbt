name := "algo_practice"

version := "0.1"

scalaVersion := "2.12.8"

val CatsVersion = "2.0.0-M1"
val CatsEffectVersion = "1.3.1"

libraryDependencies ++= Seq(
  "org.typelevel"              %% "cats-core"            % CatsVersion,
  "org.typelevel"              %% "cats-effect"          % CatsEffectVersion,
)

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-feature",
  "-Ypartial-unification",
  "-Xfatal-warnings",
)