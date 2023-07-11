package cl.uchile.dcc
package gwent.effects

import gwent.cards.*
import scala.collection.mutable.ArrayBuffer

class MoraleBoostEffect extends IUnitEffect {
  override def apply[T <: IUnitCard](card: ICard, target: ArrayBuffer[T]): Unit = {

  }

  override def copy(): IEffect = new MoraleBoostEffect()
}

