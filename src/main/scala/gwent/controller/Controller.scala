package cl.uchile.dcc
package gwent.controller

import gwent.board.*
import gwent.cards.*
import gwent.player.*
import gwent.states.*

class Controller extends IController {
  private var state: State = new MainMenu(this)

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

  override def Pass(): Unit = {
    state.Pass()
  }

  override def nextRound(): Unit = {
    state.Comply()
  }

  override def getState(): State = {
    val clone = state
    clone
  }

  override def setState(C: State): Unit = {
    state = C
  }
}
