package com.mcl.tic.tac.toe.domain

import com.mcl.tic.tac.toe.domain.UserPositionLabel._
import org.scalatest.{ FlatSpec, Matchers }

class DomainSpec extends FlatSpec with Matchers {

  behavior of "State"

  it should "complain when constructed with invalid grid dimensions" in {
    the[IllegalArgumentException] thrownBy {
      State(List(OTHER_USER))
    } should have message "requirement failed: Invalid grid dimensions"
  }

  it should "return correct empty positions" in {
    State(List(
      OTHER_USER,
      EMPTY_POSITION,
      EMPTY_POSITION,
      MACHINE_USER,
      EMPTY_POSITION,
      OTHER_USER,
      OTHER_USER,
      EMPTY_POSITION,
      MACHINE_USER
    )).getEmptyPositionIndexes() should be(Seq(1, 2, 4, 7))
  }

  it should "return correct copy" in {
    State(List(
      OTHER_USER,
      EMPTY_POSITION,
      EMPTY_POSITION,
      MACHINE_USER,
      EMPTY_POSITION,
      OTHER_USER,
      OTHER_USER,
      EMPTY_POSITION,
      MACHINE_USER
    )).copyWithNewPosition(2, MACHINE_USER) should be(
      State(List(
        OTHER_USER,
        EMPTY_POSITION,
        MACHINE_USER,
        MACHINE_USER,
        EMPTY_POSITION,
        OTHER_USER,
        OTHER_USER,
        EMPTY_POSITION,
        MACHINE_USER
      ))
    )
  }

  it should "return correct string representation" in {
    State(List(
      OTHER_USER,
      EMPTY_POSITION,
      EMPTY_POSITION,
      MACHINE_USER,
      EMPTY_POSITION,
      OTHER_USER,
      OTHER_USER,
      EMPTY_POSITION,
      MACHINE_USER
    )).getStringRepresentation() should be("O__X_OO_X")
  }

}
