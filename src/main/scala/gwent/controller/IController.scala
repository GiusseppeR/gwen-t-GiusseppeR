package cl.uchile.dcc
package gwent.controller

import gwent.states.*

import cl.uchile.dcc.gwent.notifications.PlayerControllerNotification
import cl.uchile.dcc.gwent.player.*
import cl.uchile.dcc.gwent.states.controller.ControllerState

import scala.collection.mutable.Map
import gwent.cards.*

import scala.collection.mutable
trait IController {
  def newGame():Unit
  def goToMenu():Unit
  def setPlayerName(name:String):Unit
  def setEnemyName(name:String):Unit
  def numberOfEnemies(n:Int):Unit
  def startGame():Unit
  def getUserName(): String
  def showUserHand(): List[ICard]
  def showUserGems(): Int
  def playerMap():mutable.Map[String,(String,Int)]
  def endRound():Unit
  def getState():ControllerState
  def setState(C: ControllerState): Unit
  def moveToDefeated(P:Player):Unit
  def getDefeatedPlayerNames:List[String]
  def getActivePlayerNames:List[String]

  def destroy(name:String):Unit
}
