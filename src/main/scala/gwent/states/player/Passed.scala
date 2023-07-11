package cl.uchile.dcc
package gwent.states.player

import gwent.player.Iplayer

class Passed(context: Iplayer) extends PlayerState(context){
  override def toString: String = "Passed"
  override def toDefeated(): Unit = {
    context.setState(new Defeated(context))
  }
}
