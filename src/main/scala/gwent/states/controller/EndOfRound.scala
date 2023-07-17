package cl.uchile.dcc
package gwent.states.controller

import gwent.controller.*

import cl.uchile.dcc.gwent.board.Board
class EndOfRound(context:Controller) extends ControllerState(context) {
  override def finishRound(): Unit = {
    val passed = context.getPassedPlayers.toList
    val board: Board = passed(0).getBoard()
    val winner = board.getWinner()
    for(player <- passed; if player != winner){
      player.takeDamage()
    }
    transition()
  }

  override def transition(): Unit = {
    val passed = context.getPassedPlayers.toList
    val check1 = passed.contains(context.User().get)
    val check2 = check1 && passed.length == 1
    if(!check1 || check2){
      context.setState(new MainMenu(context))
      context.reset()
    }else{
      passed.foreach(p => context.moveToPassed(p))
      context.setState(new Idle(context))
    }
  }
  override def toIdle(): Unit = {
    context.setState(new Idle(context))
  }

  override def toMainMenu(): Unit = {
    context.setState(new MainMenu(context))
  }
}
