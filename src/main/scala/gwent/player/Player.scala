package cl.uchile.dcc
package gwent.player

import gwent.board.*
import gwent.cards.*

import java.util.Objects
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/** Represents a player.
 *
 * Each player is unique, and must have a name and a deck of cards.
 *
 * @param name The name of the player.
 * @param deck The starting deck of the player.
 *
 * @constructor Creates a player with the specified name and starting deck.
 *              When created, the deck is shuffled and the player is assigned a starting number of Gems,
 *              together with a 10 cards hand.
 *
 * @example
 * {{{
 * var Deck = ArrayBuffer(Card1,Card2,....,Card25)
 * var Player1 = new Player("Arthas_LichKing123", Deck)
 *
 * var Gems = Player1.remainingGems() // 2
 * var Hand = Player1.currentHand() // ArrayBuffer(Card1,Card2,...Card10)
 * }}}
 */
class Player(private val name:String, private var deck:ArrayBuffer[ICard]) extends Iplayer {
  /** Board in which the player is involved.
   *
   * None by default.
   */
  private var board: Option[Board] = None

  /** Board side of the player.
   *
   * None by default.
   */
  private var boardSide:Option[BoardSide] = None

  /** Starting number of Gems.
   *  2 by default.
   */
  private var Gems: Int = 2

  /** Hand of the player.
   *
   * Group of cards currently available for use by the player.
   * Is limited to 10 cards.
   */
  private var Hand: ArrayBuffer[ICard] = ArrayBuffer()

  /** Links the player to an existing board.
   *
   * @param b Board to be set.
   */
  override def setBoard(b: Board): Unit = {
    board = Some(b)
  }

  /** Provides a reference to the board linked to player.
   *
   * This method is implemented for double dispatch purposes.
   * Thus, it is used only when board is not None.
   * For reference, please see the playCard method below.
   *
   * @return Board object currently linked to the player.
   */
  override def getBoard(): Board = {
    board.get
  }

  /** Assigns a board side to the player.
   *
   * @param b BoardSide to be assigned.
   */
  override def setBoardSide(side: BoardSide): Unit = {
    boardSide = Some(side)
  }

  /** Provides a reference to the board side assigned to player.
   *
   * Again, this method is implemented for double dispatch purposes.
   * Not used when boardSide is None.
   * For reference, please see the playCard method below.
   *
   * @return BoardSide object assigned to the player.
   */
  override def getBoardSide(): BoardSide = {
    boardSide.get
  }

  /** Provides the name of the player.
   *
   * @return The name assigned to the player.
   */
  override def getName(): String = name

  /** Shows the deck of the player in its current state.
   *
   * ''deck'' is an mutable ArrayBuffer, which means that it can (and will)
   * change over the course of the game. This method returns the current deck.
   *
   * @return The deck of the player, considering all changes made to it.
   */
  override def getDeck(): ArrayBuffer[ICard] = deck

  /** Shuffles the deck.
   *
   * Applies a Random method that changes the order of the cards in the deck.
   */
  private def shuffleDeck(): Unit = {
    Random.shuffle(deck)
  }

  /** Shows the amount of Gems left.
   *
   * @return The Gems variable.
   */
  override def remainingGems(): Int = Gems

  /** Makes the player lose a Gem.
   *
   * Updates the Gems variable by subtracting 1.
   */
  override def takeDamage(): Unit = {
    Gems = Gems - 1
  }

  /** Shows the hand of the player.
   *
   * @return The Hand variable.
   */
  override def currentHand(): ArrayBuffer[ICard] = Hand

  /** Represents the action of playing a card.
   *
   * The player can choose a card from its hand and play it.
   * If the card has no place to go to, then it only removes the selected card from Hand (testing purposes).
   * If the card has a place to go to, then it also starts a double dispatch process that ends up with the card in its place.
   * Possible exceptions handled in sendCommand method.
   *
   * @param C Card chosen by the player.
   */
  override def playCard(C: ICard): Unit = {
    this.currentHand() -= C
    C.sendCommand(this)
  }

  /** Takes a number of cards from the deck and adds them to the player hand.
   *
   * The cards will be removed from deck and added to Hand.
   * It takes into consideration that Hand cannot have more than 10 Cards.
   * If the number of cards requested is bigger than the amount available,
   * then all cards left will be taken.
   *
   * @param n Number of cards taken.
   */
  override def takeCard(n: Int): Unit = {
    val handSize:Int = this.currentHand().length //number of cards in Hand
    val deckLast = this.getDeck().length-1 //Index of the last element in deck
    //slice: Array of cards to be taken (a sub-array of deck)
    var slice: ArrayBuffer[ICard] = ArrayBuffer()
    // k is an auxiliary variable, it (sort of) represents the amount of cards actually taken
    var k:Int = n
    //We impose that Hand cannot have more than 10 cards
    if(k+handSize > 10){
      k = 10-handSize // k is adjusted accordingly
    }
    //If the amount of cards requested is bigger than the available, then slice = deck
    if (k >= deckLast + 1) {
      slice = this.getDeck()
    }
    //otherwise, slice is created using the slice() method of ArrayBuffer
    else {
      slice = this.getDeck().slice(deckLast - k, deckLast) //the last k cards from deck
    }
    this.currentHand() ++= slice //slice is added to Hand
    this.getDeck() --= slice  //slice is removed from deck
  }

  override def equals(obj: Any): Boolean = {
    if (obj.isInstanceOf[Player]) {
      val other = obj.asInstanceOf[Player]
      (this eq other)
    } else {
      false
    }
  }

  override def hashCode(): Int = {
    System.identityHashCode(this)
  }

  //Creation of the player hand
  this.shuffleDeck()
  this.takeCard(10)

}