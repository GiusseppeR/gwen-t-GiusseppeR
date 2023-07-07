package cl.uchile.dcc
package gwent.states.controller

import gwent.controller.*
class EndOfGame(context:Controller) extends ControllerState(context) {
  override def toMainMenu(): Unit = {
    context.setState(new MainMenu(context))
  }

  override def Comply(): Unit = {
    toMainMenu()
  }
}
