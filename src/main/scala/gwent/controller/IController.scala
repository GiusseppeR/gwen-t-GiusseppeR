package cl.uchile.dcc
package gwent.controller

import gwent.states.*

import cl.uchile.dcc.gwent.notifications.PlayerControllerNotification
import cl.uchile.dcc.gwent.player.*
import gwent.board.*

import scala.collection.mutable.Map
import gwent.cards.*

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 * Defines a controller.
 * Methods must be defined in class.
 */
trait IController {
  def newGame():Unit
  def goToMenu():Unit
  def setPlayerName(name:String):Unit
  def setEnemyName(name:String):Unit
  def numberOfRandomEnemies(n:Int):Unit
  def startGame():Unit
  def selectCard():Unit
  def Pass():Unit
  def PlayCard(n:Int):Unit
  def FinishRound():Unit
  def User():Option[Player]
  def getActivePlayers:ListBuffer[Player]
  def getPassedPlayers:ListBuffer[Player]
  def getUserName(): String
  def playerMap():mutable.Map[String,Int]
  def getState():ControllerState
  def setState(C: ControllerState): Unit
  def moveToActive(P:Player):Unit
  def moveToDefeated(P:Player):Unit
  def moveToPassed(P:Player):Unit
  def getDefeatedPlayerNames:List[String]
  def getActivePlayerNames:List[String]
  def getPassedPlayerNames:List[String]
  def reset():Unit
  def destroy(name:String):Unit
}
