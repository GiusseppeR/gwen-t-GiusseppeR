package cl.uchile.dcc
package gwent.notifications

import gwent.player.*
import cl.uchile.dcc.gwent.controller.*
import cl.uchile.dcc.gwent.observer.*

class PlayerDefeated(private val player: Player) extends PlayerControllerNotification {
  override def open(controller: Controller):Unit = {
    controller.moveToDefeated(player)
  }
}
