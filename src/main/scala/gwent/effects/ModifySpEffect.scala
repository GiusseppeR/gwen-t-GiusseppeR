package cl.uchile.dcc
package gwent.effects
import gwent.cards.IUnitCard

/** Class for effects that modify the SP of the cards.
 *
 * @param f function to be applied to the SP of the cards.
 * @param applyToItself Boolean that indicates if the effect gets applied to the card that has it.
 */
abstract class ModifySpEffect(f:Int => Int, applyToItself:Boolean) extends AbstractEffect{
  selfApply = applyToItself
  protected val function: Int => Int = f

  override def apply(target: IUnitCard): Unit
}
