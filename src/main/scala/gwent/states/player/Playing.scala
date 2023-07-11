package cl.uchile.dcc
package gwent.states.player

import gwent.player.Iplayer

class Playing(context: Iplayer) extends PlayerState(context){
  override def toString: String = "Playing"

  override def toDefeated(): Unit = {
    context.setState(new Defeated(context))
  }
}
