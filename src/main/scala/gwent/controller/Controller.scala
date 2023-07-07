package cl.uchile.dcc
package gwent.controller

import gwent.board.*
import gwent.cards.*
import gwent.player.*
import gwent.states.*

import cl.uchile.dcc.gwent.notifications.PlayerControllerNotification
import cl.uchile.dcc.gwent.states.controller.{ControllerState, MainMenu}

class Controller extends IController {
  private var state: ControllerState = new MainMenu(this)
  private var currentPlayer: Option[Player] = None

  override def newGame(): Unit = {
    state.toGameConfiguration()
  }

  override def goToMenu(): Unit = {
    state.toMainMenu()
  }

  override def addToDeck(): Unit = {

  }

  override def addCard(): Unit = {

  }

  override def setName(): Unit = {

  }

  override def startGame(): Unit = {
    state.toGameStart()
  }

  override def prepareRound(): Unit = {
    state.toRoundStart()
  }
  override def startRound(): Unit = {
    state.Comply()
  }

  override def playCard(): Unit = {
    state.Comply()
  }

  override def setPlayer(): Unit = {

  }

  override def getPlayer(): Option[Player] = currentPlayer

  override def endRound(): Unit = {
  }

  override def nextRound(): Unit = {
    state.Comply()
  }

  override def getState(): ControllerState = {
    val clone = state
    clone
  }

  override def setState(C: ControllerState): Unit = {
    state = C
  }

  override def playerUpdate(notification: PlayerControllerNotification): Unit = {

  }
}
