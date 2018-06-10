package com.mcl.tic.tac.toe.machine.learning

import com.mcl.tic.tac.toe.domain.State

object StateEvaluationService {

  private var losingStates: Set[String] = Set()

  def getNextState(state: State): State = {
    val emptyPositionsIndexes = state.getEmptyPositionIndexes
    if (emptyPositionsIndexes.nonEmpty) {
      val chosenPosition = emptyPositionsIndexes
        .collectFirst { case p if getReward(state, p) > 0 => p }
        .getOrElse(emptyPositionsIndexes.head)
      state.copyWithNewPosition(chosenPosition, "X")
    } else {
      state
    }
  }

  private def getReward(state: State, probableNextPosition: Int): Int = {
    if (losingStates.exists(s => startsWith(s, state, probableNextPosition)))
      // Some terminal failure state had similar pattern, so negative reward
      -1
    else
      1
  }

  private def startsWith(losingStateRepresentation: String, state: State, probableNextPosition: Int): Boolean = {
    val probableNextStatePrefix = state.getStringRepresentation().substring(0, probableNextPosition) + "X"
    losingStateRepresentation.startsWith(probableNextStatePrefix)
  }

  def registerTerminalStateResultingInLoss(state: State): List[String] = {
    losingStates += state.getStringRepresentation()
    losingStates.toList
  }

}
