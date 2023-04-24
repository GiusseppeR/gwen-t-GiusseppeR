package cl.uchile.dcc
package gwent

import java.util.Objects
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/** Defines a Player.
 *
 * Each method must be defined in class.
 * Used by Player.
 */
trait Iplayer {
  def getName(): String
  def getDeck(): ArrayBuffer[Card]
  def remainingGems(): Int
  def takeDamage():Unit
  def currentHand(): ArrayBuffer[Card]
  def playCard(C:Card): Unit
  def takeCard(n:Int):Unit
}

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
class Player(private val name:String, private var deck:ArrayBuffer[Card]) extends Iplayer {
  /** Starting number of Gems.
   *  2 by default.
   */
  private var Gems: Int = 2
  /** Hand of the player.
   *
   * Group of cards currently available for use by the player.
   * Is limited to 10 cards.
   */
  private var Hand: ArrayBuffer[Card] = ArrayBuffer()

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
  override def getDeck(): ArrayBuffer[Card] = deck

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
  override def currentHand(): ArrayBuffer[Card] = Hand

  /** Represents the effect that playing a card has in Player.
   *
   * The player will choose a card to play, and it will be removed of its hand.
   *
   * @param C Card chosen by the player.
   */
  override def playCard(C: Card): Unit = {
    this.currentHand() -= C
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
    val handSize:Int = this.currentHand().length
    val deckLast = this.getDeck().length-1
    var slice: ArrayBuffer[Card] = ArrayBuffer()
    var k:Int = n
    if( n > deckLast+1 ){
      slice = this.getDeck()
    }
    if(k+handSize > 10){
      k = 10-handSize
    }
    if (deckLast == 0 && k !=0){
      slice = this.getDeck()
    } else {
      slice = this.getDeck().slice(deckLast - k, deckLast)
    }
    this.currentHand() ++= slice
    this.getDeck() --= slice
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
    Objects.hash(classOf[Player])
  }
  this.shuffleDeck()
  this.takeCard(10)

}
