package com.mcl.tic.tac.toe.server

import akka.actor.{ ActorRef, ActorSystem }
import akka.event.Logging
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import akka.pattern.ask
import akka.util.Timeout
import com.mcl.tic.tac.toe.server.TicTacToeActor.GetState

import scala.concurrent.Future
import scala.concurrent.duration._

trait TicTacToeRoutes extends JsonSupport {

  implicit def system: ActorSystem

  lazy val log = Logging(system, classOf[TicTacToeRoutes])

  def ticTacToeActor: ActorRef

  implicit lazy val timeout = Timeout(5.seconds)

  lazy val ticTacToeRoutes: Route =
    pathPrefix("games") {
      path(Segment) { gameSessionId =>
        concat(
          get {
            val maybeGameState: Future[Option[GameState]] =
              (ticTacToeActor ? GetState(gameSessionId)).mapTo[Option[GameState]]
            rejectEmptyResponse {
              complete(maybeGameState)
            }
          }
        )
      }
    }
}
