package com.mcl.tic.tac.toe.server

import akka.actor.{ ActorSystem, Props }
import akka.testkit.{ ImplicitSender, TestKit }
import com.mcl.tic.tac.toe.domain.{ State, UserPositionLabel }
import com.mcl.tic.tac.toe.domain.UserPositionLabel.{ EMPTY_POSITION, OTHER_USER, MACHINE_USER }
import com.mcl.tic.tac.toe.server.TicTacToeActor.GetNextState
import org.scalatest.{ BeforeAndAfterAll, Matchers, WordSpecLike }

class TicTacToeActorSpec extends TestKit(ActorSystem("MySpec")) with ImplicitSender
    with WordSpecLike with Matchers with BeforeAndAfterAll {

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "A TicTacToe actor" must {

    "send back some new state with only one new position" in {
      val ticTacToe = system.actorOf(Props[TicTacToeActor])
      val state = State(List(
        OTHER_USER,
        MACHINE_USER,
        MACHINE_USER,
        OTHER_USER,
        OTHER_USER,
        MACHINE_USER,
        EMPTY_POSITION,
        OTHER_USER,
        EMPTY_POSITION
      ))

      ticTacToe ! GetNextState(state)
      val nextState = receiveN(1).head.asInstanceOf[State]

      nextState shouldNot be(state)
      val differenceInPositions = state.grid.zip(nextState.grid).filter(entry => entry._1 != entry._2)
      differenceInPositions.size shouldBe 1
      differenceInPositions.head._1 shouldBe EMPTY_POSITION
      differenceInPositions.head._2 shouldBe MACHINE_USER
    }

  }

}
