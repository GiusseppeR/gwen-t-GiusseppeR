package cl.uchile.dcc
package gwent.controller

import gwent.board.*
import gwent.cards.*
import gwent.player.*
import gwent.states.*

import cl.uchile.dcc.gwent.notifications.PlayerControllerNotification
import cl.uchile.dcc.gwent.states.controller.{ControllerState, MainMenu}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Map
import scala.util.Random

class Controller extends IController {
  private var state: ControllerState = new MainMenu(this)
  private var user: Option[Iplayer] = None
  private var playerList: ListBuffer[Iplayer] = ListBuffer()
  private var currentPlayer: Option[Iplayer] = None

  private val activePlayers:ListBuffer[Iplayer] = ListBuffer()
  private val defeatedPlayers:ListBuffer[Iplayer] = ListBuffer()
  private val passedPlayer:ListBuffer[Iplayer] = ListBuffer()

  override def newGame(): Unit = {
    state.toGameConfiguration()
  }

  override def goToMenu(): Unit = {
    state.toMainMenu()
  }

  override def setPlayerName(name:String): Unit = state.setName(name)

  override def numberOfEnemies(n: Int): Unit = state.setNumberOfEnemies(n)
  override def setEnemyName(name:String): Unit = state.setEnemy(name)
  override def getUserName(): String = user.get.getName()
  override def showUserHand(): List[ICard] = user.get.currentHand().toList
  override def showUserGems(): Int = user.get.remainingGems()
  override def startGame(): Unit = {
    playerList = state.startGame()
    user = Some(playerList.head)
    playerList.foreach(p => p.addController(this))
    activePlayers ++= Random.shuffle(playerList)
    state.toIdle()
  }
  override def playerMap(): mutable.Map[String, (String,Int)] = {
    val map:mutable.Map[String,(String,Int)] = mutable.Map()
    playerList.foreach(p => map += (p.getName() -> ( p.getState(),p.remainingGems() ) ) )
    map
  }
  override def endRound(): Unit = {
    state.toEOR()
  }

  override def getState(): ControllerState = {
    state
  }

  override def setState(C: ControllerState): Unit = {
    state = C
  }

  override def playerUpdate(notification: PlayerControllerNotification): Unit = {
    notification.open(this)
  }

  override def moveToDefeated(P: Iplayer): Unit = {
    activePlayers -= P
    defeatedPlayers += P
  }

  override def getActivePlayerNames: List[String] = {
    val output = activePlayers.map(p => p.getName())
    output.toList
  }

  override def getDefeatedPlayerNames: List[String] = {
    val output = defeatedPlayers.map(p => p.getName())
    output.toList
  }

  override def destroy(name:String):Unit = {
    state.toEOR()
    var Player:Iplayer = new Player("",ArrayBuffer())
    for(C <- playerList){
      if(C.getName() == name){
        Player = C
      }
    }
    Player.takeDamage()
    Player.takeDamage()
  }

}
