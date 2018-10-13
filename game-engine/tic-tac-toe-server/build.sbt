lazy val akkaHttpVersion = "10.0.10"
lazy val akkaVersion    = "2.5.4"

dockerBaseImage := "openjdk:8-jre-alpine"
packageName in Docker := "tic-tac-toe-app"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization    := "com.mcl",
      scalaVersion    := "2.12.4"
    )),
    name := "tic-tac-toe-server",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-testkit"         % akkaVersion     % Test,
      "org.scalatest"     %% "scalatest"            % "3.0.1"         % Test
    )
  ).enablePlugins(JavaAppPackaging, AshScriptPlugin)
