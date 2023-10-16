package cl.uchile.dcc
package gwent.notifications

import gwent.controller.*

import cl.uchile.dcc.gwent.observer.*
import cl.uchile.dcc.gwent.player.Player
/** Represents a notification that states that a player has passed.
 *
 * @param player Player that has passed.
 */

class PlayerPassed(private val player:Player) extends PlayerControllerNotification {
  /** Tells the controller to move the player from the activePlayers to the passedPlayers.
   *
   * @param controller Controller.
   */
  override def open(controller: Controller): Unit = {
    controller.moveToPassed(player)
  }

}
