package cl.uchile.dcc
package gwent.states.controller

import gwent.controller.*
import gwent.states.player.PlayerState

import cl.uchile.dcc.gwent.AI.BasicAI
import cl.uchile.dcc.gwent.player.Player
class Idle(context:Controller) extends ControllerState(context) {

   if (context.User().nonEmpty){
     val active = context.getActivePlayers
     val turn = active.slice(0,active.indexOf(context.User().get))

     turn.foreach(p => BasicAI(p))
   }
   override def pass():Unit = context.User().get.pass()
   override def toCardSelection(): Unit = {
     context.setState(new CardSelection(context))
   }
   override def toPassed(): Unit = {
    context.setState(new Passed(context))
   }
}
