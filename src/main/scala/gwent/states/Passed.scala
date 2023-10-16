package cl.uchile.dcc
package gwent.states

import gwent.AI.BasicAI
import gwent.controller.*
import gwent.player.Player
import gwent.states.{ControllerState, EndOfRound, Idle}

/** Passed State.
 * When the user passes, the controller enters passed state.
 * The rest of the players play until they have all passed, and then transtitions to End of round.
 *
 * @param context A controller as context.
 */
class Passed(context:Controller) extends ControllerState(context) {
  /**Makes the rest of the players play.
   */
  override def pass():Unit = {
    var active: List[Player] = context.getActivePlayers.toList
    while (active.nonEmpty) {
      active.foreach(p => BasicAI(p))
      active = context.getActivePlayers.toList
    }
  }

  /**
   *transition to EndOfRound.
   */
  override def toEOR(): Unit = {
    context.setState(new EndOfRound(context))
  }
}
