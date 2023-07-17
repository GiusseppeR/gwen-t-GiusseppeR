package cl.uchile.dcc
package gwent.states.controller

import gwent.controller.*

import cl.uchile.dcc.gwent.AI.BasicAI
import cl.uchile.dcc.gwent.player.Player

class Passed(context:Controller) extends ControllerState(context) {

  override def pass():Unit = {
    var active: List[Player] = context.getActivePlayers.toList
    while (active.nonEmpty) {
      active.foreach(p => BasicAI(p))
      active = context.getActivePlayers.toList
    }
  }

  override def toIdle(): Unit = {
    context.setState(new Idle(context))
  }

  override def toEOR(): Unit = {
    context.setState(new EndOfRound(context))
  }
}
