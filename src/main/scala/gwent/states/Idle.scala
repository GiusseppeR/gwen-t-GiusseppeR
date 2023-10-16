package cl.uchile.dcc
package gwent.states

import gwent.AI.BasicAI
import gwent.controller.*
import gwent.player.Player
import gwent.states.{CardSelection, ControllerState, Passed}

/**Idle state.
 *
 * When the controller enters idle state, every player before the user plays.
 * Then, in idle state, the user can choose to select card or pass.
 *
 * @param context A controller as context.
 */
class Idle(context:Controller) extends ControllerState(context) {
   if (context.User().nonEmpty){
     val active = context.getActivePlayers
     val turn = active.slice(0,active.indexOf(context.User().get))

     turn.foreach(p => BasicAI(p))
   }

  /**
   * makes the user's player pass.
   */
  override def pass():Unit = context.User().get.pass()

  /**
   * transition to cardSelection.
   */
   override def toCardSelection(): Unit = {
     context.setState(new CardSelection(context))
   }

  /**
   * transition to Passed.
   */
   override def toPassed(): Unit = {
    context.setState(new Passed(context))
   }
}
