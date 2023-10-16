package cl.uchile.dcc
package gwent.player.factories

import scala.collection.mutable.ArrayBuffer
import gwent.player.*
import gwent.cards.*

/**Represents a player factory.
 *
 * Used by GameConfiguration state to create enemies for the user.
 */
class PlayerFactory extends IPlayerFactory {
  /**
   * name of the player to be produced.
   */
  private var name:Option[String] = None
  /**
   * deck of the player to be produced.
   */
  private var deck:Option[ArrayBuffer[ICard]] = None

  /** Sets the name of the player.
   *
   * @param n Name of the player.
   */
  override def setName(n:String): Unit = {
    name = Some(n)
  }

  /** Sets the deck of the player.
   *
   * @param d Deck of the player.
   */
  override def setDeck(d:ArrayBuffer[ICard]): Unit = {
    deck = Some(d)
  }

  /** Creates a player.
   *
   * @return A player.
   */
  override def createPlayer():Player = {
    new Player(name.get,deck.get)
  }
}
