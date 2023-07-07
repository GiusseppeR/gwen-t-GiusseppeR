package cl.uchile.dcc
package gwent.controller

import gwent.states.*

import cl.uchile.dcc.gwent.notifications.PlayerControllerNotification
import cl.uchile.dcc.gwent.player.{Iplayer, Player}
import cl.uchile.dcc.gwent.states.controller.ControllerState
trait IController {
  def newGame():Unit
  def goToMenu():Unit
  def addToDeck():Unit
  def addCard():Unit
  def setName():Unit
  def startGame():Unit
  def prepareRound():Unit
  def startRound():Unit
  def setPlayer():Unit
  def getPlayer():Option[Player]
  def playCard():Unit
  def endRound():Unit
  def nextRound():Unit
  def getState():ControllerState
  def setState(C: ControllerState): Unit
  def playerUpdate(notification: PlayerControllerNotification):Unit
}
