package cl.uchile.dcc
package gwent.notifications

import gwent.player.*
import cl.uchile.dcc.gwent.controller.*
import cl.uchile.dcc.gwent.observer.*

class PlayerDefeated(player: Player) extends PlayerControllerNotification {
  override def open(controller: Controller):Unit = {
    controller.moveToDefeated(player)
  }
}
