package cl.uchile.dcc
package gwent.states.controller

import gwent.controller.*

import cl.uchile.dcc.gwent.AI.BasicAI

class CardSelection(context:Controller) extends ControllerState(context) {
  override def playCard(n: Int): Unit = {
    val card = context.User().get.currentHand()(n)
    context.User().get.playCard(card)

    val active = context.getActivePlayers.toList
    val turns = active.slice(active.indexOf(context.User().get)+1,active.length)
    turns.foreach(p => BasicAI(p) )
  }
  override def toIdle(): Unit = {
    context.setState(new Idle(context))
  }
}
