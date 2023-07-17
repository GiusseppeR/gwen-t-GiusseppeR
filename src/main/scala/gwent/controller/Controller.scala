package cl.uchile.dcc
package gwent.controller

import gwent.board.*
import gwent.cards.*
import gwent.player.*
import gwent.states.*

import cl.uchile.dcc.gwent.notifications.*
import cl.uchile.dcc.gwent.observer.*
import cl.uchile.dcc.gwent.states.controller.{ControllerState, EndOfRound, MainMenu}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Map
import scala.util.Random

class Controller extends IController with Observer[PlayerControllerNotification] {
  private var state: ControllerState = new MainMenu(this)
  private var user: Option[Player] = None
  private var playerList: ListBuffer[Player] = ListBuffer()

  private val activePlayers:ListBuffer[Player] = ListBuffer()
  private val defeatedPlayers:ListBuffer[Player] = ListBuffer()
  private val passedPlayers:ListBuffer[Player] = ListBuffer()
  override def newGame(): Unit = {
    state.toGameConfiguration()
  }

  override def goToMenu(): Unit = {
    state.toMainMenu()
  }

  override def FinishRound(): Unit = {
    state.finishRound()
  }
  override def setPlayerName(name:String): Unit = state.setPlayerName(name)
  override def numberOfRandomEnemies(n: Int): Unit = state.setNumberOfRandomEnemies(n)
  override def setEnemyName(name:String): Unit = state.setEnemy(name)
  override def User():Option[Player] = user

  override def getActivePlayers: ListBuffer[Player] = activePlayers

  override def getPassedPlayers: ListBuffer[Player] = passedPlayers
  override def getUserName(): String = user.get.getName()
  override def showUserHand(): List[ICard] = user.get.currentHand().toList
  override def showUserGems(): Int = user.get.remainingGems()
  override def startGame(): Unit = {
    val settings = state.gameStartSettings
    playerList = settings(1)
    user = Some(settings.head)
    playerList.foreach(p => p.addObserver(this) )
    activePlayers ++= playerList
    state.toIdle()
  }

  override def selectCard(): Unit = {
    state.toCardSelection()
  }

  override def Pass(): Unit = {
    state.pass()
    state.toPassed()
    state.pass()
    state.toEOR()
  }
  override def PlayCard(n: Int): Unit = {
    state.playCard(n)
    state.toIdle()
  }

  override def playerMap(): mutable.Map[String, (String,Int)] = {
    val map:mutable.Map[String,(String,Int)] = mutable.Map()
    playerList.foreach(p => map += (p.getName() -> ( p.getState(),p.remainingGems() ) ) )
    map
  }
  override def getState(): ControllerState = {
    state
  }

  override def setState(C: ControllerState): Unit = {
    state = C
  }

  override def update(observable:Subject[PlayerControllerNotification], value: PlayerControllerNotification): Unit = {
    value.open(this)
  }
  override def moveToActive(P: Player): Unit = {
    activePlayers += P
    passedPlayers -= P
  }
  override def moveToDefeated(P: Player): Unit = {
    activePlayers -= P
    defeatedPlayers += P
  }
  override def moveToPassed(P: Player): Unit = {
    activePlayers -= P
    passedPlayers += P
  }

  override def getActivePlayerNames: List[String] = {
    val output = activePlayers.map(p => p.getName())
    output.toList
  }

  override def getDefeatedPlayerNames: List[String] = {
    val output = defeatedPlayers.map(p => p.getName())
    output.toList
  }

  override def getPassedPlayerNames: List[String] = {
    val output = passedPlayers.map(p => p.getName())
    output.toList
  }

  override def reset(): Unit = {
    user.empty
    playerList.empty
    activePlayers.empty
    passedPlayers.empty
    defeatedPlayers.empty
  }
  override def destroy(name:String):Unit = {
    setState(new EndOfRound(this))
    for(player <- activePlayers.toList){
      if(name == player.getName()){
        player.takeDamage()
        player.takeDamage()
      }
    }
  }

}
