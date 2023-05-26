package cl.uchile.dcc
package gwent.cards

import gwent.player.*

/** Defines a card.
 *
 * Methods must be specified in class.
 * Used by AbstractCard and its subclasses
 */
trait ICard{
  def getName():String
  def sendCommand(P:Player):Unit
}