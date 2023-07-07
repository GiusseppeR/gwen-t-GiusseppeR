package cl.uchile.dcc
package gwent.states.controller

import gwent.controller.*

import cl.uchile.dcc.gwent.states.player.PlayerState
class Idle(context:Controller) extends ControllerState(context) {
  private val Player = context.getPlayer()
  private var PlayerState: PlayerState = Player.getState()



}
