package cl.uchile.dcc
package gwent.states

import gwent.board.*
import gwent.cards.*
import gwent.controller.*
import gwent.player.*
import gwent.states.*

import scala.collection.mutable.ListBuffer

/** Represents a generic Controller State
 *
 * methods and transitions must be defined in subclasses, according to the state.
 *
 * @param context A controller as context.
 */

class ControllerState(private var context:Controller) extends State{
  def setNumberOfRandomEnemies(n:Int):Unit= throw InvalidFunctionException("This function cannot be used in this state")
  def setPlayerName(name:String):Unit = throw InvalidFunctionException("This function cannot be used in this state")
  def setEnemy(name:String):Unit = throw InvalidFunctionException("This function cannot be used in this state")
  def gameStartSettings:(Player,ListBuffer[Player]) = {
    throw InvalidFunctionException("This function cannot be used in this state")
  }
  def pass():Unit = throw InvalidFunctionException("This function cannot be used in this state")
  def playCard(n:Int):Unit = throw InvalidFunctionException("This function cannot be used in this state")
  def finishRound():Unit = throw InvalidFunctionException("This function cannot be used in this state")
  def transition():Unit = throw InvalidFunctionException("This function cannot be used in this state")
  def toMainMenu():Unit = {
    transitionError("Main Menu")
  }
  def toGameConfiguration(): Unit = {
   transitionError("GameConfiguration")
  }
  def toCardSelection():Unit = {
    transitionError("CardSelection")
  }
  def toIdle(): Unit = {
    transitionError("toFirst")
  }

  def toPassed(): Unit = {
    transitionError("Passed")
  }
  def toEOR(): Unit = {
    transitionError("EndOfRound")
  }

}
