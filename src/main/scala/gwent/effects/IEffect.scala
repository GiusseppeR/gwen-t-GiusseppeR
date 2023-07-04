package cl.uchile.dcc
package gwent.effects

import gwent.cards.*

import scala.collection.mutable.ArrayBuffer

trait IEffect{
  def apply[T <: IUnitCard](card: ICard, target:ArrayBuffer[T]):Unit
}
