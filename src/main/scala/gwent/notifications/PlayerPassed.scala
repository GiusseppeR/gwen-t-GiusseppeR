package cl.uchile.dcc
package gwent.notifications

import gwent.controller.*

import cl.uchile.dcc.gwent.observer.*
import cl.uchile.dcc.gwent.player.Player

class PlayerPassed(private val player:Player) extends PlayerControllerNotification {
  override def open(controller: Controller): Unit = {
    controller.moveToPassed(player)
  }

}
