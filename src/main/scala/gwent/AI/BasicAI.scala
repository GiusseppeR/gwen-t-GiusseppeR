package cl.uchile.dcc
package gwent.AI

import gwent.player.Player
import gwent.cards.*

import scala.util.Random

/**A simple AI model for the CPU players.
 *
 * Made for testing purposes.
 * When applied to a player, they play a random card.
 *
 */
object BasicAI extends AI{
  /** Applies the model to a player.
   *
   * @param P Player implementing the model.
   */
  override def apply(P: Player): Unit = {
    val card = P.currentHand()(Random.nextInt(P.currentHand().length))
    P.playCard(card)
  }
}
