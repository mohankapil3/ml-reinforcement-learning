package com.mcl.tic.tac.toe.server

import akka.actor.{ Actor, ActorLogging, Props }

final case class GameState(positions: Array[String])

object TicTacToeActor {

  final case class GetState(gameSessionId: String)

  def props: Props = Props[TicTacToeActor]
}

class TicTacToeActor extends Actor with ActorLogging {

  import TicTacToeActor._

  var inPlayGames = Map.empty[String, GameState]

  def receive: Receive = {
    case GetState(gameSessionId) =>
      sender() ! inPlayGames.get(gameSessionId)
  }
}
