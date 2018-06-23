package com.mcl.tic.tac.toe.machine.learning

import com.mcl.tic.tac.toe.domain.State
import com.mcl.tic.tac.toe.domain.UserPositionLabel.{ EMPTY_POSITION, MACHINE_USER, OTHER_USER }
import org.scalatest.{ FlatSpec, Matchers }

class StateEvaluationServiceSpec extends FlatSpec with Matchers {

  behavior of "StateEvaluationService"

  it should "learn from past losses and should not return any state as next state which could lead to game loss" in {
    // Choose some state which results in machine user loss and register it with game engine.
    // If other user is given turn on this state, it will win.
    val losingState = State(List(
      OTHER_USER,
      MACHINE_USER,
      MACHINE_USER,
      OTHER_USER,
      EMPTY_POSITION,
      EMPTY_POSITION,
      EMPTY_POSITION,
      EMPTY_POSITION,
      EMPTY_POSITION
    ))

    StateEvaluationService.registerStateResultingInLoss(losingState)

    // Now if other user plays game with similar pattern of past moves
    val currentState = State(List(
      OTHER_USER,
      EMPTY_POSITION,
      EMPTY_POSITION,
      EMPTY_POSITION,
      EMPTY_POSITION,
      EMPTY_POSITION,
      EMPTY_POSITION,
      EMPTY_POSITION,
      EMPTY_POSITION
    ))

    // Then game engine should choose different next available position
    val expectedNextState = State(List(
      OTHER_USER,
      EMPTY_POSITION,
      MACHINE_USER,
      EMPTY_POSITION,
      EMPTY_POSITION,
      EMPTY_POSITION,
      EMPTY_POSITION,
      EMPTY_POSITION,
      EMPTY_POSITION
    ))

    StateEvaluationService.getNextState(currentState) should be(expectedNextState)
  }

}
