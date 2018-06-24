package com.mcl.tic.tac.toe.server

import com.mcl.tic.tac.toe.domain.{ Outcome, State }
import spray.json.DefaultJsonProtocol

trait CustomJsonSupport extends DefaultJsonProtocol {

  implicit val gameStateJsonFormat = jsonFormat1(State)
  implicit val processingOutcomeJsonFormat = jsonFormat1(Outcome)

}
