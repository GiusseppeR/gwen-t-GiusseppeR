package cl.uchile.dcc
package gwent.cards.ref

import gwent.cards.*

/**
 * Reference of a Range card.
 */
object RangeRef extends Ref{
  /** Checks if a card is a Range card.
   *
   * @param C Card to be checked.
   * @return A boolean value, result of the check.
   */
  override def typeCheck(C: IUnitCard): Boolean = {
    C match {
      case _: Range => true
      case _ => false
    }
  }
}
