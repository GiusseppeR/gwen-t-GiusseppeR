package cl.uchile.dcc
package gwent.states.controller

import gwent.controller.*
class MainMenu(context:Controller) extends ControllerState(context) {
  override def toGameConfiguration(): Unit = {
    context.setState(new GameConfiguration(context))
  }

}
