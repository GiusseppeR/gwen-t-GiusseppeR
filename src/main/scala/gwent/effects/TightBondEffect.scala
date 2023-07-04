package cl.uchile.dcc
package gwent.effects
import gwent.cards.*

import scala.collection.mutable.ArrayBuffer

class TightBondEffect extends IUnitEffect{
  override def apply[T <: IUnitCard](card: ICard, target: ArrayBuffer[T]): Unit = {
    target.foreach{ C => if(card.getName() == C.getName())
      C.setSP(2 * C.getSP()) }
  }
  override def copy(): IEffect = new TightBondEffect()
}
