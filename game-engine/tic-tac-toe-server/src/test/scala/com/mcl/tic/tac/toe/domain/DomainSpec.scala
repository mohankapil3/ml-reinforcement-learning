package com.mcl.tic.tac.toe.domain

import org.scalatest.{FlatSpec, Matchers}

class DomainSpec extends FlatSpec with Matchers {

  behavior of "State"

  it should "complain when constructed with invalid grid dimensions" in {
    the[IllegalArgumentException] thrownBy {
      State(List("O"))
    } should have message "requirement failed: Invalid grid dimensions"
  }

  it should "return correct empty positions" in {
    State(List("O", "", "", "X", "", "O", "O", "", "X")).getEmptyPositionIndexes() should be(Seq(1, 2, 4, 7))
  }

  it should "return correct copy" in {
    State(List("O", "", "", "X", "", "O", "O", "", "X")).copyWithNewPosition(2, "X") should be(State(List("O", "", "X", "X", "", "O", "O", "", "X")))
  }

  it should "return correct string representation" in {
    State(List("O", "", "", "X", "", "O", "O", "", "X")).getStringRepresentation() should be("O__X_OO_X")
  }

}
