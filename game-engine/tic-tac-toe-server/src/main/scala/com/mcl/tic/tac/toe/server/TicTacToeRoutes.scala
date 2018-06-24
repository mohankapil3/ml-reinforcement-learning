package com.mcl.tic.tac.toe.server

import akka.actor.{ ActorRef, ActorSystem }
import akka.event.Logging
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import akka.pattern.ask
import akka.util.Timeout
import com.mcl.tic.tac.toe.domain.{ Outcome, State }
import com.mcl.tic.tac.toe.server.TicTacToeActor.{ GetNextState, ReportLoss }

import scala.concurrent.Future
import scala.concurrent.duration._

trait TicTacToeRoutes extends SprayJsonSupport with CustomJsonSupport {

  implicit def system: ActorSystem

  lazy val log = Logging(system, classOf[TicTacToeRoutes])

  def ticTacToeActor: ActorRef

  implicit lazy val timeout = Timeout(5.seconds)

  lazy val ticTacToeRoutes: Route =
    pathPrefix("tic-tac-toe") {
      post {
        path("next-state") {
          entity(as[State]) { state =>
            val nextState: Future[State] = (ticTacToeActor ? GetNextState(state)).mapTo[State]
            onComplete(nextState) {
              nextState => complete(nextState)
            }
          }
        }
      } ~
        post {
          path("report-loss") {
            entity(as[State]) { state =>
              val outcome: Future[Outcome] = (ticTacToeActor ? ReportLoss(state)).mapTo[Outcome]
              onComplete(outcome) {
                outcome => complete(outcome)
              }
            }
          }
        }
    }

}
