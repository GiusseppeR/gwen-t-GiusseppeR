package cl.uchile.dcc
package gwent.states.controller

import gwent.controller.*
import gwent.states.controller.*

class RoundStart(context:Controller) extends ControllerState(context) {
  override def toIdle(): Unit = {
    context.setState(new Idle(context))
  }
  override def Comply():Unit = {
    toIdle()
  }
}
