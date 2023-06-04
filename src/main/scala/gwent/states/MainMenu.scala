package cl.uchile.dcc
package gwent.states

import gwent.controller.*
class MainMenu(context:Controller) extends State(context) {
  override def toGameConfiguration(): Unit = {
    context.setState(new GameConfiguration(context))
  }

}
