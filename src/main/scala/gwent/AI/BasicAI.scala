package cl.uchile.dcc
package gwent.AI

import gwent.player.Player
import gwent.cards.*

import scala.util.Random

object BasicAI extends AI{

  override def apply(P: Player): Unit = {
    val card = P.currentHand()(Random.nextInt(P.currentHand().length))
    P.playCard(card)
  }
}
