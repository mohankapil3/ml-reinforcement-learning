package com.mcl.tic.tac.toe.server

import com.mcl.tic.tac.toe.domain.State
import spray.json.DefaultJsonProtocol

trait CustomJsonSupport extends DefaultJsonProtocol {

  implicit val gameStateJsonFormat = jsonFormat1(State)

}
