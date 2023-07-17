package cl.uchile.dcc
package gwent.board

import gwent.cards.*
import gwent.player.*
import gwent.board.*

import cl.uchile.dcc.gwent.effects.IEffect
import cl.uchile.dcc.gwent.observer.AbstractSubject

import scala.collection.mutable.ArrayBuffer

/** Represents a board.
 *
 * Each board is unique.
 * A board has a zone to store weather cards,
 * and it can have an unlimited amount of players associated.
 *
 * Part of an observer pattern.
 * The Board is being observed by all the cards played by all players.
 * Whenever a Weather Card is placed, it notifies the observers of the new effect in place.
 *
 * No parameters required.
 *
 * @constructor Creates an empty board.
 *
 * @example
 * {{{
 * var Deck = ArrayBuffer(Card1,Card2,....,Card25)
 * var Player1 = new Player("Arthas_LichKing123", Deck)
 * var Player2 = new Player("Lord_Fordring", Deck)
 * var Player3 = new Player("Sylvanas_Renegade", Deck)
 *
 * val Board = new Board()
 * Board.addPlayer("Player1", "Ice Crown Citadel")
 * Board.addPlayer("Player2", "Argent Crusade Outpost")
 * Board.addPlayer("Player2", "Ruins of Lordaeron")
 * }}}
 */
class Board extends AbstractSubject[IEffect] with IBoard {
  /** Stores the weather cards played.
   * Has a Clear Weather card in as default.
   */
  private var weatherZone:ArrayBuffer[WeatherCard] = ArrayBuffer(new WeatherCard("Clear Weather"))

  /**Stores references to the players in the board
   */
  private var playerList: ArrayBuffer[Player] = ArrayBuffer()

  /** Links a player to the board and assigns them a board side.
   *
   * Cannot receive a player already in.
   * Cannot assign a board side already occupied.
   * Makes use of setBoard and setBoardSide methods from Player class.
   *
   * @param P Player entering the board.
   * @param sideName name identifier of their board side.
   */
  override def addPlayer(P:Player, sideName: String): Unit = {
    try{
      /* first, the method verifies if it can actually add the player and put them in sideName*/
      for (player <- playerList) {
        if (P.equals(player) || sideName == player.getBoardSide().getName()) throw
          new Exception()
      }
      /*If no exception is thrown, then proceeds*/
      P.setBoard(this)                          /*player methods*/
      P.setBoardSide(new BoardSide(sideName))
      playerList += P
    }catch{ /*exception handler*/
      case a:Exception => print("Invalid placement. Board side taken or player already in board")
    }
  }

  /** Provides the list of players in board.
   *
   * @return Array with references to the players in board.
   */
  override def getPlayerList():ArrayBuffer[Player] = {
    val clone = playerList.clone()
    clone
  }

  /** Determines the winner of the round.
   *
   * Implements an algorithm that compares the points of each player's board side, and returns the winner.
   * There could be no winner; in which case it returns a "phantom player" that is not in the game.
   *
   * @return The player with highest score, or a phantom player.
   */
  override def getWinner(): Player = {
    /*current highest score*/
    var max:Int = 0
    /*current winner */
    var winner: Option[Player] = None
    /*auxiliary variable, it helps a bit with the efficiency*/
    var playerPoints:Int = 0
    /*Determines if the match is tied*/
    var tie:Boolean = false

    for(player <- playerList){
      /*The score of the current player is calculated*/
      playerPoints = player.getBoardSide().getPoints()
      /*if it's higher than max, then the winner is set to player*/
      if (playerPoints > max){
        max = playerPoints
        winner = Some(player)
        tie = false

        /*otherwise, we know that there's a tie up until now*/
      } else if (max > 0 && max == playerPoints){
        tie = true
      }
    }
    if(!tie) {
      winner.get /*returns winner*/
    }else{
      new Player("",ArrayBuffer()) /*returns phantom player*/
    }

  }

  /** Provides the weather card currently in effect.
   *
   * @return first element of weatherZone
   */
  override def getCurrentWeatherCard(): WeatherCard = {
    val clone = weatherZone(0)

    clone
  }

  /** Sets a new weather card in effect.
   *
   * Given a weather card, it sets it as the first element in weatherZone.
   * It notifies all the cards en every BoardSide of the new effect in place.
   *
   * @param C Weather card to be placed.
   */
  override def setWeatherCard(C:WeatherCard):Unit = {
    weatherZone.prepend(C)
    notifyObservers(C.getEffect())
  }

  override def equals(obj: Any): Boolean = {
    if (obj.isInstanceOf[Board]) {
      val other = obj.asInstanceOf[Board]
      (this eq other)
    } else {
      false
    }
  }

  override def hashCode(): Int = {
    System.identityHashCode(this)
  }
}
