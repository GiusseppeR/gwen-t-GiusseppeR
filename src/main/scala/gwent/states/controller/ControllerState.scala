package cl.uchile.dcc
package gwent.states.controller

import gwent.board.*
import gwent.cards.*
import gwent.controller.*
import gwent.player.*

import cl.uchile.dcc.gwent.states.*

import scala.collection.mutable.ListBuffer

class ControllerState(private var context:Controller) extends State{
  def Comply():Unit = {}
  def setNumberOfEnemies(n:Int):Unit = {}
  def setName(name:String):Unit = {}
  def setEnemy(name:String):Unit = {}
  def startGame():ListBuffer[Player] = ListBuffer()
  def toMainMenu():Unit = {
    transitionError("Main Menu")
  }
  def toGameConfiguration(): Unit = {
   transitionError("GameConfiguration")
  }

  def toGameStart(): Unit = {
    transitionError("toGameStart")
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

  def toRoundStart(): Unit = {
    transitionError("RoundStart")
  }

  def toEndOfGame(): Unit = {
    transitionError("EndOfGame")
  }

}
