package cl.uchile.dcc
package gwent.effects.unit

import gwent.cards.*
import gwent.cards.ref.*
import gwent.effects.ModifySpEffect
import java.util.Objects
import scala.collection.mutable.ArrayBuffer

/**Represent a TightBond effect.
 *
 * Inherits ModifySpEffect.
 * By default, it doubles the SP of the card that has it, and of those of the same name in the same row.
 *
 */
class TightBondEffect extends ModifySpEffect((x:Int) => 2*x,true){
  /** Secondary constructor.
   * Allows the effect to act upon a specific row.
   *
   * @param reference Reference object of the row.
   */
  def this(reference:Ref) = {
    this()
    ref = Some(reference)
  }

  /** Applies the effect to a card target.
   *
   * @param target Card target,
   */
  override def apply(target: IUnitCard): Unit = {
    val cardName = card.get.getName()
    val targetName = target.getName()
    val condition = (cardName == targetName)

    if (selfApply){
      card.get.setSP(function)
      selfApply = false
    }

    if (ref.get.typeCheck(target) && condition){
      target.setSP(function)
    }
  }

  override def equals(obj: Any): Boolean = {
    obj match {
      case _:TightBondEffect => true
      case _ => false
    }
  }

  override def hashCode(): Int = {
    Objects.hash(classOf[TightBondEffect])
  }

}
