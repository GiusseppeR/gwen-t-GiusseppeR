package cl.uchile.dcc
package gwent.cards.ref

import gwent.cards.*

/**
 * Reference of a siege card.
 */
case object SiegeRef extends Ref {
  /** Checks if a card is a Siege card.
   *
   * @param C Card to be checked.
   * @return A boolean value, result of the check.
   */
  override def typeCheck(C: IUnitCard): Boolean = {
    C match {
      case _: Siege => true
      case _ => false
    }
  }
}
