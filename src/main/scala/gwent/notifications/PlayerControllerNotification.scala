package cl.uchile.dcc
package gwent.notifications

import gwent.controller.*
import gwent.player.*
import gwent.observer.*

/** Defines a Player-Controller notification.
 *
 * The controller can receive a notification from the player and open it.
 * The open method gives a series of instructions to the Controller.
 */
trait PlayerControllerNotification {
  def open(controller: Controller):Unit
}
