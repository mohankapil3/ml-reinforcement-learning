package com.mcl.tic.tac.toe.domain

final case class State(grid: Array[String]) {

  def getStringRepresentation(): String = grid.map(position => if (position == "") "_" else position).mkString("")

  def getEmptyPositionIndexes(): Seq[Int] = grid.zipWithIndex.filter(entry => entry._1 == "").map(_._2)

  def copyWithNewPosition(position: Int, value: String): State = {
    val copyOfGrid = new Array[String](grid.length)
    Array.copy(grid, 0, copyOfGrid, 0, grid.length)
    val result = copy(grid = copyOfGrid)
    result.grid(position) = value
    result
  }

}

