package cl.uchile.dcc
package gwent.cards.factories

import gwent.effects.IEffect

import cl.uchile.dcc.gwent.cards.ICard

/** Represents a generic card factory.
 *
 * Implements the CardFactory Trait.
 * No parameters required.
 *
 * @tparam T Type of the card to be produced. Covariant.
 */

abstract class AbstractCardFactory[T <:ICard] extends CardFactory[T] {
  /** Effect of the card to be produced.
   * None by default.
   */
  protected var Effect:Option[IEffect] = None
  /** Name of the card to be produced.
   * None by default.
   */
  protected var Name:Option[String] = None
  /** Strenght Points of the card to be produced
   * None by default.
   */
  protected var SP:Option[Int] = None

  override def createCard(): T

  /** Allows to set the effect of the card.
   *
   * @param effect Effect of the card to be produced.
   */
  override def setEffect(effect: IEffect): Unit = {
    Effect = Some(effect)
  }

  /** Allows to set the name of the card.
   *
   * @param name Name of the card to be produced.
   */
  override def setName(name: String): Unit = {
    Name = Some(name)
  }

  /** Allows to set the number of strength points of the card.
   *
   * @param n Number of strength points of the card to be produced.
   */
  override def setSP(n: Int): Unit = {
    SP = Some(n)
  }
}
