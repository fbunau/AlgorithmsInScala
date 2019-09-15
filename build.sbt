name := "algo_practice"

version := "0.1"

scalaVersion := "2.12.8"

val CatsVersion = "2.0.0"
val CatsEffectVersion = "1.3.1"
val ParboiledVersion = "2.1.8"

val ScalaTestVersion = "3.0.8"

libraryDependencies ++= Seq(
  "org.typelevel"              %% "cats-core"            % CatsVersion,
  "org.typelevel"              %% "cats-effect"          % CatsEffectVersion,
  "org.parboiled"              %% "parboiled"            % ParboiledVersion,

  "org.scalatest"              %% "scalatest"            % ScalaTestVersion       % Test,
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