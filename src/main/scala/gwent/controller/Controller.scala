package cl.uchile.dcc
package gwent.controller

import gwent.board.*
import gwent.cards.*
import gwent.player.*
import gwent.states.*

import cl.uchile.dcc.gwent.exceptions.NoPlayerNameException
import cl.uchile.dcc.gwent.notifications.*
import cl.uchile.dcc.gwent.observer.*

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Map
import scala.util.Random

/** Represents the Controller.
 *
 * Connects the Model to the View.
 * Part of an Observer Pattern. The controller observes the players in the game.
 */

class Controller extends IController with Observer[PlayerControllerNotification] {
  private var state: ControllerState = new MainMenu(this)
  private var user: Option[Player] = None
  private var playerList: ListBuffer[Player] = ListBuffer()

  private val activePlayers:ListBuffer[Player] = ListBuffer()
  private val defeatedPlayers:ListBuffer[Player] = ListBuffer()
  private val passedPlayers:ListBuffer[Player] = ListBuffer()

  /** Goes to the game configuration screen.
   * Only works in MainMenu state
   */
  override def newGame(): Unit = {
    state.toGameConfiguration()
  }

  /** Goes to the main menu.
   * Only works in GameConfiguration state.
   */
  override def goToMenu(): Unit = {
    state.toMainMenu()
  }

  /**Finishes the round.
   * If the user is defeated or wins, it goes to Main Menu.
   * If not, it goes to Idle state.
   */
  override def FinishRound(): Unit = {
    state.finishRound()
  }

  /** Sets the name of the User player.
   *
   * @param name Name of the player associated to the user.
   */
  override def setPlayerName(name:String): Unit = state.setPlayerName(name)

  /** Sets an amount of random enemies.
   *
   * @param n Numer of random enemies.
   */
  override def numberOfRandomEnemies(n: Int): Unit = state.setNumberOfRandomEnemies(n)

  /** Sets the name of an enemy for the user.
   *
   * @param name Name of the enemy.
   */
  override def setEnemyName(name:String): Unit = state.setEnemy(name)

  /**Provides access to the user player.
   *
   * @return Player associated with the user.
   */
  override def User():Option[Player] = user

  /** Provides a list of players that haven't passed nor been defeated.
   *
   * @return activePlayers variable.
   */
  override def getActivePlayers: ListBuffer[Player] = activePlayers

  /** Provides a list of players that have passed.
   *
   * @return passedPlayers variable.
   */
  override def getPassedPlayers: ListBuffer[Player] = passedPlayers

  /** Provides the player name set by the user.
   * @return Name of the player associated with the user.
   */
  override def getUserName(): String = user.get.getName()

  /**Starts a game.
   * Goes from GameConfiguration to Idle state.
   * sets the playerList, user and activePlayers variable.
   */
  override def startGame(): Unit = {
    try{
      val settings = state.gameStartSettings
      playerList = settings(1)
      user = Some(settings.head)
      playerList.foreach(p => p.addObserver(this))
      activePlayers ++= playerList
      state.toIdle()
    }catch{
      case e:NoPlayerNameException => println("No player name was chosen, please choose one.")
    }
  }

  /** Goes from Idle state to CardSelection state or Passed state,
   *  depending on the amount of cards left in the user's hand.
   */
  override def selectCard(): Unit = {
      if user.get.currentHand().isEmpty then state.toPassed() else state.toCardSelection()
  }

  /**Passes the turn of the user.
   *
   * When the user passes, the rest of the players play until they all pass as well.
   * Goes from Idle, to Passed, then iterates until each player passes, and goes to EndOfRound.
   */
  override def Pass(): Unit = {
    state.pass()
    state.toPassed()
    state.pass()
    state.toEOR()
  }

  /** Allows the user to play a card.
   *
   * @param n Index of the card in the hand.
   */
  override def PlayCard(n: Int): Unit = {
    state.playCard(n)
    state.toIdle()
  }

  /**Provides a map with the names of the players and the amount of gems they have left.
   *
   * @return Map with the names of the players and the amount of gems they have.
   */

  override def playerMap(): mutable.Map[String,Int] = {
    val map:mutable.Map[String,Int] = mutable.Map()
    playerList.foreach(p => map += (p.getName() -> p.remainingGems()) )
    map
  }

  /** Provides the state of the controller.
   *
   * Implemented for testing purposes.
   *
   * @return The current state of the controller.
   */
  override def getState(): ControllerState = {
    state
  }

  /**Sets the state of the controller.
   *
   * @param C new state of the controller.
   */
  override def setState(C: ControllerState): Unit = {
    state = C
  }

  /** updates the controller whenever a player passes or dies.
   *
   * @param observable Player that changed.
   * @param value Notification.
   */
  override def update(observable:Subject[PlayerControllerNotification], value: PlayerControllerNotification): Unit = {
    value.open(this)
  }

  /** Moves a player to the activePlayers list.
   *
   * @param P Player to be moved.
   */
  override def moveToActive(P: Player): Unit = {
    activePlayers += P
    passedPlayers -= P
  }

  /** Moves a player to the defeatedPlayers list.
   *
   * @param P Player to be moved.
   */
  override def moveToDefeated(P: Player): Unit = {
    activePlayers -= P
    defeatedPlayers += P
  }

  /** Moves a player to the passedPlayers list.
   *
   * @param P Player to be moved.
   */
  override def moveToPassed(P: Player): Unit = {
    activePlayers -= P
    passedPlayers += P
  }

  override def getActivePlayerNames: List[String] = {
    val output = activePlayers.map(p => p.getName())
    output.toList
  }

  /**Provides a list with the names of the defeated players.
   *
   * @return List with the names of the defeated players.
   */

  override def getDefeatedPlayerNames: List[String] = {
    val output = defeatedPlayers.map(p => p.getName())
    output.toList
  }

  /** Provides a list with the names of the passed players.
   *
   * @return List with the names of the passed players.
   */
  override def getPassedPlayerNames: List[String] = {
    val output = passedPlayers.map(p => p.getName())
    output.toList
  }

  /**
   * resets the local variables of the controller.
   */
  override def reset(): Unit = {
    user.empty
    playerList.empty
    activePlayers.empty
    passedPlayers.empty
    defeatedPlayers.empty
  }

  /** Kills a player.
   * Implemented for testing purposes.
   *
   * @param name Name of the player to be destroyed.
   */
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
