package cl.uchile.dcc
package gwent.states

import gwent.AI.BasicAI
import gwent.controller.*
import gwent.states.{ControllerState, Idle}

/** Card selection state.
 *
 * The user can play a card in this state.
 *
 * @param context A controller as context.
 */

class CardSelection(context:Controller) extends ControllerState(context) {
  /** Tells the player associated to the user, to play a card.
   *
   * @param n Index of the card in the hand.
   */
  override def playCard(n: Int): Unit = {
    val card = context.User().get.currentHand()(n)
    context.User().get.playCard(card)
    val active = context.getActivePlayers.toList
    val turns = active.slice(active.indexOf(context.User().get)+1,active.length)
    turns.foreach(p => BasicAI(p) )
  }

  /**
   * Transitions to Idle state.
   */
  override def toIdle(): Unit = {
    context.setState(new Idle(context))
  }
}
