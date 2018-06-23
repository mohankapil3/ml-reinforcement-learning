package com.mcl.tic.tac.toe.machine.learning

import com.mcl.tic.tac.toe.domain.State
import com.mcl.tic.tac.toe.domain.UserPositionLabel._

import scala.collection.mutable.Set

object StateEvaluationService {

  // mutable concurrent set to hold states which lead game engine to lose
  private val losingStates: Set[String] = {
    import scala.collection.JavaConverters._
    java.util.Collections.newSetFromMap(new java.util.concurrent.ConcurrentHashMap[String, java.lang.Boolean]).asScala
  }

  def getNextState(state: State): State = {
    val emptyPositionsIndexes = state.getEmptyPositionIndexes
    if (emptyPositionsIndexes.nonEmpty) {
      val chosenPosition = emptyPositionsIndexes
        .collectFirst { case p if getReward(state, p) > 0 => p }
        .getOrElse(emptyPositionsIndexes.head)
      state.copyWithNewPosition(chosenPosition, MACHINE_USER)
    } else {
      state
    }
  }

  def registerStateResultingInLoss(state: State): List[String] = {
    losingStates += state.getStringRepresentation()
    losingStates.toList
  }

  private def getReward(state: State, probableNextPosition: Int): Int = {
    if (losingStates.exists(s => startsWith(s, state, probableNextPosition)))
      // Some losing state had similar pattern, so negative reward
      -1
    else
      // No known losing state with similar pattern, so positive reward
      1
  }

  private def startsWith(losingStateRepresentation: String, state: State, probableNextPosition: Int): Boolean = {
    val probableNextStatePrefix = state.getStringRepresentation().substring(0, probableNextPosition) + MACHINE_USER
    losingStateRepresentation.startsWith(probableNextStatePrefix)
  }

}
