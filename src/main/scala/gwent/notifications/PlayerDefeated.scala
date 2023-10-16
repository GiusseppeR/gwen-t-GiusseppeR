package cl.uchile.dcc
package gwent.notifications

import gwent.player.*
import cl.uchile.dcc.gwent.controller.*
import cl.uchile.dcc.gwent.observer.*

/** Represents a notification that states that a player has been defeated.
 *
 * @param player Player that has been defeated.
 */
class PlayerDefeated(private val player: Player) extends PlayerControllerNotification {
  /** Tells the controller to move the player from the activePlayers to the defeatedPlayers.
   *
   * @param controller Controller.
   */
  override def open(controller: Controller):Unit = {
    controller.moveToDefeated(player)
  }
}
