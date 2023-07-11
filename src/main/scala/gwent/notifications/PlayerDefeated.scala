package cl.uchile.dcc
package gwent.notifications

import gwent.player.Iplayer

import cl.uchile.dcc.gwent.controller.*

class PlayerDefeated(P:Iplayer) extends PlayerControllerNotification {
  override def open(controller: Controller):Unit = {
    controller.moveToDefeated(P)
  }
}
