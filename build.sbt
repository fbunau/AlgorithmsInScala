name := "algo_practice"

version := "0.1"

scalaVersion := "2.13.1"

val CatsVersion = "2.1.0-RC2"
val CatsEffectVersion = "2.0.0"
val ParboiledVersion = "2.1.8"
val ScalaCheckVersion = "1.14.2"

val ScalaTestVersion = "3.1.0"

libraryDependencies ++= Seq(
  "org.typelevel"              %% "cats-core"            % CatsVersion,
  "org.typelevel"              %% "cats-effect"          % CatsEffectVersion,
  "org.parboiled"              %% "parboiled"            % ParboiledVersion,

  "org.scalacheck"             %% "scalacheck"           % ScalaCheckVersion,

  "org.scalatest"              %% "scalatest"            % ScalaTestVersion       % Test,
)

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-language:existentials",
  "-unchecked",
  "-feature",
  "-Xfatal-warnings",
  "-explaintypes",
  "-Xcheckinit",
  "-Xfatal-warnings",

  //"-Xlint:adapted-args",
  "-Xlint:nullary-unit",
  "-Xlint:inaccessible",
  "-Xlint:nullary-override",
  "-Xlint:infer-any",
  "-Xlint:missing-interpolator",
  "-Xlint:doc-detached",
  "-Xlint:private-shadow",
  "-Xlint:type-parameter-shadow",
  "-Xlint:poly-implicit-overload",
  "-Xlint:option-implicit",
  "-Xlint:delayedinit-select",
  "-Xlint:package-object-classes",
  "-Xlint:stars-align",
  "-Xlint:constant",
  "-Xlint:unused",
  "-Xlint:nonlocal-return",
  "-Xlint:implicit-not-found",
  "-Xlint:serial",
  "-Xlint:valpattern",
  "-Xlint:eta-zero",
  "-Xlint:eta-sam",
  "-Xlint:deprecation",

  "-Ywarn-dead-code",
  "-Ywarn-extra-implicit",
  "-Ywarn-octal-literal",
  "-Ywarn-value-discard",
)