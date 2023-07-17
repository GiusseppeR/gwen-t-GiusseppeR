package cl.uchile.dcc
package gwent.states

import gwent.board.Board
import gwent.controller.*
import gwent.states.{ControllerState, Idle, MainMenu}

/**End of round state.
 *
 * When the round is finished, it can have three outcomes:
 * The user wins -> back to main menu
 * the user loses -> back to main menu
 * the user keeps playing -> back to idle
 *
 * @param context A controller as context.
 */
class EndOfRound(context:Controller) extends ControllerState(context) {
  /** Finishes the round.
   * Establishes a winner and makes everyone else take damage.
   */
  override def finishRound(): Unit = {
    val passed = context.getPassedPlayers.toList
    val board: Board = passed(0).getBoard()
    val winner = board.getWinner()
    for(player <- passed; if player != winner){
      player.takeDamage()
    }
    transition()
  }

  /**Decides the transition, according to the results after finishing round.
   */
  override def transition(): Unit = {
    val passed = context.getPassedPlayers.toList
    val check1 = passed.contains(context.User().get)
    val check2 = check1 && passed.length == 1
    if(!check1 || check2){
      context.setState(new MainMenu(context))
      context.reset()
    }else{
      passed.foreach(p => context.moveToActive(p))
      context.setState(new Idle(context))
    }
  }

  /**
   * transition to idle
   */
  override def toIdle(): Unit = {
    context.setState(new Idle(context))
  }

  /**
   * transition to main menu.
   */
  override def toMainMenu(): Unit = {
    context.setState(new MainMenu(context))
  }
}
