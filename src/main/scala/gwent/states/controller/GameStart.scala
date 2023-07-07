package cl.uchile.dcc
package gwent.states.controller

import gwent.controller.*

class GameStart(context:Controller) extends ControllerState(context) {
  override def toRoundStart(): Unit = {
    context.setState(new RoundStart(context))
  }
}
