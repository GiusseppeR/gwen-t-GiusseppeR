package cl.uchile.dcc
package gwent.cards

import gwent.board.*
import gwent.cards.*
import gwent.cards.ref.*
import gwent.player.*

import cl.uchile.dcc.gwent.effects.*

/** Helps to define a UnitCard.
 *
 * Methods must be specified in class.
 * Used by AbstractUnitCard and its subclasses
 */
trait IUnitCard extends ICard{
  def setSP(f: Int => Int):Unit
  def overrideSp(n:Int):Unit
  def getSP():Int
  def getStackedSP():Int
  def goToZone(B: BoardSide):Unit
  def callRef():Ref
}
