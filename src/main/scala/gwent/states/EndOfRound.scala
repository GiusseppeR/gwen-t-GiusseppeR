package cl.uchile.dcc
package gwent.states

import gwent.controller.*
class EndOfRound(context:Controller) extends State(context) {
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
