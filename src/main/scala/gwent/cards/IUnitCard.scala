package cl.uchile.dcc
package gwent.cards

import gwent.board.*
import gwent.cards.*
import gwent.player.*

import cl.uchile.dcc.gwent.effects.*

/** Helps to define a UnitCard.
 *
 * Methods must be specified in class.
 * Used by AbstractUnitCard and its subclasses
 */
trait IUnitCard{
  def setSP(n:Int):Unit
  def getSP():Int
  def goToZone(B: BoardSide):Unit
  def update(C:ICard,effect: IEffect):Unit
  def getName():String
}
