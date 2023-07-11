package cl.uchile.dcc
package gwent.states.controller

import gwent.controller.*

import gwent.states.player.PlayerState
class Idle(context:Controller) extends ControllerState(context) {
  override def toEOR(): Unit = {
    context.setState(new EndOfRound(context))
  }
}
