package cl.uchile.dcc
package gwent.cards

import gwent.player.*

import cl.uchile.dcc.gwent.effects.IEffect

/** Defines a card.
 *
 * Methods must be specified in class.
 * Used by AbstractCard and its subclasses
 */
trait ICard{
  def getName():String
  def sendCommand(P:Player):Unit
  def getEffect():IEffect
}