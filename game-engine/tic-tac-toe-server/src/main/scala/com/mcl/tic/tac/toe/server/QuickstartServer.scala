package com.mcl.tic.tac.toe.server

import akka.actor.{ ActorRef, ActorSystem }
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer

import scala.concurrent.{ ExecutionContext, Future }
import scala.io.StdIn

object QuickstartServer extends App with TicTacToeRoutes with CorsSupport {

  implicit val system: ActorSystem = ActorSystem("tic-tac-toe-server")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  implicit val executionContext: ExecutionContext = system.dispatcher

  val ticTacToeActor: ActorRef = system.actorOf(TicTacToeActor.props, "ticTacToeActor")

  lazy val routes: Route = corsHandler(ticTacToeRoutes)

  val serverBindingFuture: Future[ServerBinding] = Http().bindAndHandle(routes, "localhost", 8080)

  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")

  StdIn.readLine()

  serverBindingFuture
    .flatMap(_.unbind())
    .onComplete { done =>
      done.failed.map { ex => log.error(ex, "Failed unbinding") }
      system.terminate()
    }
}
