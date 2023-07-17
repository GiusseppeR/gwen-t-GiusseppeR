package cl.uchile.dcc
package gwent.cards.ref

import gwent.cards.*

/**Reference of a Close Combat card..
 */
object CloseCombatRef extends Ref{
  /**Checks if a card is a CloseCombat card
   *
   * @param C Card to be checked.
   * @return A boolean value, result of the check.
   */
  override def typeCheck(C: IUnitCard): Boolean = {
    C match {
      case _: CloseCombat => true
      case _ => false
    }
  }
}
