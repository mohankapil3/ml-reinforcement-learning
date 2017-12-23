package com.mcl.tic.tac.toe.server

import akka.actor.{ Actor, ActorLogging, Props }

import scala.util.Random

final case class State(grid: Array[String])

object TicTacToeActor {

  final case class GetNextState(state: State)

  def props: Props = Props[TicTacToeActor]
}

class TicTacToeActor extends Actor with ActorLogging {

  import TicTacToeActor._

  def receive: Receive = {
    case GetNextState(state) =>
      sender() ! evaluateNextState(state)
  }

  private def evaluateNextState(state: State): State = {
    // Dumb engine, picks up random next available move
    val emptyPositionsIndex = state.grid.zipWithIndex.filter(entry => entry._1 == "").map(_._2)
    if (!emptyPositionsIndex.isEmpty) {
      val nextMoveIndex = getRandom(emptyPositionsIndex)
      state.grid(nextMoveIndex) = "X"
    }
    state
  }

  private def getRandom(items: Seq[Int]): Int = {
    val random = new Random
    val randomIndex = random.nextInt(items.length)
    items(randomIndex)
  }

}
