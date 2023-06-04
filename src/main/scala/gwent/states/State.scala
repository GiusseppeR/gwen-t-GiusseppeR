package cl.uchile.dcc
package gwent.states

import gwent.board.*
import gwent.cards.*
import gwent.player.*
import gwent.controller.*

class State(private var context:Controller){
  def Comply():Unit = {

  }
  def Pass():Unit = {

  }
  def toMainMenu():Unit = {
    transitionError("Main Menu")
  }
  def toGameConfiguration(): Unit = {
   transitionError("GameConfiguration")
  }

  def toGameStart(): Unit = {
    transitionError("toGameStart")
  }

  def toFirst(): Unit = {
    transitionError("toFirst")
  }

  def toSecond(): Unit = {
    transitionError("toSecond")
  }

  def toPassed(): Unit = {
    transitionError("Passed")
  }

  def toEOR(): Unit = {
    transitionError("EndOfRound")
  }

  def toRoundStart(): Unit = {
    transitionError("RoundStart")
  }

  def toEndOfGame(): Unit = {
    transitionError("EndOfGame")
  }

  private def transitionError(targetState: String): Unit = {
    throw new InvalidTransitionException(
      s"Cannot transition from ${getClass.getSimpleName} to $targetState"
    )
  }

}
