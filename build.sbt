val Http4sVersion       = "0.20.0"
val CatsVersion         = "2.0.0"
val ZioCatsVersion      = "2.0.0.0-RC3"
val ZioVersion          = "1.0.0-RC13"
val LogbackVersion      = "1.2.3"
val MongoDBVersion		= "2.9.0"

lazy val root = (project in file("."))
  .settings(
    organization := "zio-http4s",
    name := "mongo-zio-hello",
    version := "0.1",
    scalaVersion := "2.12.8",
    scalacOptions ++= Seq("-Ypartial-unification"),
    libraryDependencies ++= Seq(
      "org.typelevel"              %% "cats-effect"         % CatsVersion,
      "dev.zio"                    %% "zio"                 % ZioVersion,
      "dev.zio"                    %% "zio-interop-cats"    % ZioCatsVersion,
      "org.http4s"                 %% "http4s-blaze-server" % Http4sVersion,
      "org.http4s"                 %% "http4s-dsl"          % Http4sVersion,
      "ch.qos.logback"             %  "logback-classic"     % LogbackVersion,
	  "org.mongodb.scala"		   %% "mongo-scala-driver"	% MongoDBVersion
   ),
    addCompilerPlugin("org.spire-math" %% "kind-projector"     % "0.9.6"),
    addCompilerPlugin("com.olegpy"     %% "better-monadic-for" % "0.2.4")
  )

scalacOptions ++= Seq(
  "-deprecation",               // Emit warning and location for usages of deprecated APIs.
  "-encoding", "UTF-8",         // Specify character encoding used by source files.
  "-language:higherKinds",      // Allow higher-kinded types
  "-language:postfixOps",       // Allows operator syntax in postfix position (deprecated since Scala 2.10)
  "-feature",                   // Emit warning and location for usages of features that should be imported explicitly.
  "-Ypartial-unification",      // Enable partial unification in type constructor inference
  "-Xfatal-warnings",           // Fail the compilation if there are any warnings
)