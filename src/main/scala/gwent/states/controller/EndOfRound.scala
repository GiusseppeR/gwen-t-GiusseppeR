package cl.uchile.dcc
package gwent.states.controller

import gwent.controller.*
class EndOfRound(context:Controller) extends ControllerState(context) {
  override def toRoundStart():Unit = {
    context.setState(new RoundStart(context))
  }

  override def toEndOfGame(): Unit = {
    context.setState(new EndOfGame(context))
  }

  override def Comply(): Unit = {
    toEndOfGame()
    /*Placeholder. To be implemented*/
  }
}
