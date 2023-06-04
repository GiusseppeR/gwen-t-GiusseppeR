package cl.uchile.dcc
package gwent.states

import gwent.controller.*
class EndOfGame(context:Controller) extends State(context) {
  override def toMainMenu(): Unit = {
    context.setState(new MainMenu(context))
  }

  override def Comply(): Unit = {
    toMainMenu()
  }
}
