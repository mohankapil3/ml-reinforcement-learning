package com.mcl.tic.tac.toe.domain

import scala.collection.mutable.ListBuffer

final case class State(grid: List[String]) {

  require(grid.length == 9, "Invalid grid dimensions")

  def getEmptyPositionIndexes(): Seq[Int] = grid.zipWithIndex.filter(entry => entry._1 == "").map(_._2)

  def copyWithNewPosition(position: Int, value: String): State = {
    val copyOfGrid = ListBuffer(grid: _*)
    copyOfGrid(position) = value
    State(copyOfGrid.toList)
  }

  def getStringRepresentation(): String = grid.map(position => if (position == UserPositionLabel.EMPTY_POSITION) "_" else position).mkString("")

  override def toString: String = "State(" + getStringRepresentation + ")"

}

object UserPositionLabel {

  val MACHINE_USER = "X"

  val OTHER_USER = "O"

  val EMPTY_POSITION = ""

}

