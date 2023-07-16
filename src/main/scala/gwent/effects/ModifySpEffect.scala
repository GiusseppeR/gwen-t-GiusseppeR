package cl.uchile.dcc
package gwent.effects
import gwent.cards.IUnitCard

abstract class ModifySpEffect(f:Int => Int, applyToItself:Boolean) extends AbstractEffect{
  selfApply = applyToItself
  protected val function: Int => Int = f

  override def apply(target: IUnitCard): Unit
}
