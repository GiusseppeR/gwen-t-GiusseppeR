package cl.uchile.dcc
package gwent.effects.unit

import gwent.cards.*
import gwent.effects.ModifySpEffect
import cl.uchile.dcc.gwent.cards.ref.*
import java.util.Objects
import scala.collection.mutable.ArrayBuffer

/** Represents the MoraleBoost effect.
 *
 * Inherits ModifySpEffect.
 *
 * By default, it adds 1 point to the SP of each card in the same row of the card that has it.
 *
 */
class MoraleBoostEffect extends ModifySpEffect((x:Int) => x+1,false){
  /**Secondary constructor.
   * Allows the effect to act upon a specific row.
   *
   * @param reference Reference object of the row.
   */
  def this(reference: Ref) = {
    this()
    ref = Some(reference)
  }

  /** Applies the effect to a card target.
   *
   * @param target Card target,
   */
  override def apply(target: IUnitCard): Unit = {
    if ref.get.typeCheck(target) then target.setSP(function)
  }

  override def equals(obj: Any): Boolean = {
    obj match {
      case _: MoraleBoostEffect => true
      case _ => false
    }
  }

  override def hashCode(): Int = {
    Objects.hash(classOf[MoraleBoostEffect])
  }
}

