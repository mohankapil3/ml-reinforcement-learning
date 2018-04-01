package com.mcl.tic.tac.toe.machine.learning

import com.mcl.tic.tac.toe.domain.State

object StateEvaluationService {

  private var losingStates: Set[String] = Set()

  def getNextState(state: State): State = {
    val emptyPositionsIndexes = state.getEmptyPositionIndexes
    if (emptyPositionsIndexes.nonEmpty) {
      emptyPositionsIndexes.map(p => state.copyWithNewPosition(p, "X"))
        .collectFirst { case s if getReward(s) > 0 => s }
        .getOrElse(state.copyWithNewPosition(emptyPositionsIndexes.head, "X"))
    } else {
      state
    }
  }

  private def getReward(probableNextState: State): Int = {
    if (losingStates.exists(s => s.startsWith(probableNextState.getStringRepresentation())))
      // Some terminal failure state had similar pattern, so negative reward
      -1
    else
      1
  }

  def registerTerminalStateResultingInLoss(state: State): List[String] = {
    losingStates += state.getStringRepresentation()
    losingStates.toList
  }

}
