package cl.uchile.dcc
package gwent.cards

import gwent.board.*
import gwent.cards.*
import gwent.player.*

/** Helps to define a UnitCard.
 *
 * Methods must be specified in class.
 * Used by AbstractUnitCard and its subclasses
 */
trait IUnitCard extends AbstractCard{
  def getSP():Int
  def goToZone(B: BoardSide):Unit
}
