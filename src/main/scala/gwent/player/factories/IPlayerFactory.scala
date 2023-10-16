package cl.uchile.dcc
package gwent.player.factories

import gwent.player.*

import cl.uchile.dcc.gwent.cards.*

import scala.collection.mutable.ArrayBuffer

/** Defines a player factory
 *
 * Methods must be defined in class.
 */
trait IPlayerFactory {
  def setDeck(D:ArrayBuffer[ICard]):Unit
  def setName(name: String):Unit
  def createPlayer():Player
}
