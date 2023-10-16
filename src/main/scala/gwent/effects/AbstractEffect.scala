package cl.uchile.dcc
package gwent.effects

import gwent.cards.*
import gwent.cards.ref.*

/** Represents a generic effect.
 */
abstract class AbstractEffect extends IEffect {
  /** Variable that indicates if an effect is applied to the card that has it.
   */
  protected var selfApply = false
  /**
   * Card with the effect.
   */
  protected var card:Option[IUnitCard] = None
  /**
   * Reference object.
   */
  protected var ref:Option[Ref] = None

  /** Sets the card variable.
   *
   * @param C Card with the effect.
   */
  override def setCard(C: IUnitCard): Unit = {
    card = Some(C)
    if ref.isEmpty then ref = Some(C.callRef())
  }

  /** Provides the reference object.
   *
   * @return Reference object.
   */
  override def getReference: Option[Ref] = ref
  override def apply(target: IUnitCard): Unit

}
