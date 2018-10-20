package com.mcl.tic.tac.toe.server

import akka.actor.{ActorRef, ActorSystem}
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

object TicTacToeAppServer extends App with TicTacToeRoutes {

  implicit val system: ActorSystem = ActorSystem("tic-tac-toe-server")
  implicit val executionContext: ExecutionContext = system.dispatcher

  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val ticTacToeActor: ActorRef = system.actorOf(TicTacToeActor.props, "ticTacToeActor")

  lazy val log = Logging(system, classOf[TicTacToeRoutes])

  lazy val routes: Route = ticTacToeRoutes

  Http()
    .bindAndHandle(routes, "localhost", 8080)
    .onComplete {
      case Success(b) => log.info(s"Application is up and running at ${b.localAddress.getHostName}:${b.localAddress.getPort}")
      case Failure(e) => log.error(s"Could not start application: {}", e.getMessage)
    }
}
