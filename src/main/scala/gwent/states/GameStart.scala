package cl.uchile.dcc
package gwent.states

import gwent.controller.*
class GameStart(context:Controller) extends State(context) {
  override def toRoundStart(): Unit = {
    context.setState(new RoundStart(context))
  }
}
