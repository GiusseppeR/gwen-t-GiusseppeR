package cl.uchile.dcc
package gwent.states

import gwent.controller.*
class GameConfiguration(context:Controller) extends State(context){
  override def toMainMenu(): Unit = {
    context.setState(new MainMenu(context))
  }

  override def toGameStart(): Unit = {
    context.setState(new GameStart(context))
  }
}
