package com.mcl.tic.tac.toe.server

import akka.actor.{ Actor, ActorLogging, Props }
import com.mcl.tic.tac.toe.domain.State
import com.mcl.tic.tac.toe.machine.learning.StateEvaluationService

object TicTacToeActor {

  final case class GetNextState(state: State)
  final case class ReportLoss(terminalState: State)

  def props: Props = Props[TicTacToeActor]
}

class TicTacToeActor extends Actor with ActorLogging {

  import TicTacToeActor._

  def receive: Receive = {
    case GetNextState(state) =>
      sender() ! StateEvaluationService.getNextState(state)
    case ReportLoss(terminalState) =>
      sender() ! StateEvaluationService.registerTerminalStateResultingInLoss(terminalState)
  }

}
