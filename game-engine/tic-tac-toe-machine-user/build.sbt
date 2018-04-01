lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization    := "com.mcl",
      scalaVersion    := "2.12.4"
    )),
    name := "tic-tac-toe-machine-user",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.0.1" % Test
    )
  )
