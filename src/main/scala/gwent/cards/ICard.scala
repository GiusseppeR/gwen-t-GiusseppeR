package cl.uchile.dcc
package gwent.cards

import gwent.player.*

/** Defines a card.
 *
 * getName must be specified in class.
 * Used by Card and its subclasses
 */
trait ICard{
  def getName():String
  def sendCommand(P:Player):Unit
}