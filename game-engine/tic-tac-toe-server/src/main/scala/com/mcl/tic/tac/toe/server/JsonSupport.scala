package com.mcl.tic.tac.toe.server

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

trait JsonSupport extends SprayJsonSupport {

  import DefaultJsonProtocol._

  implicit val gameStateJsonFormat = jsonFormat1(State)

}
