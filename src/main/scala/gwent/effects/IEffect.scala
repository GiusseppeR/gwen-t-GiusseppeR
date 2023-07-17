package cl.uchile.dcc
package gwent.effects

import gwent.cards.*
import gwent.cards.ref.*
import scala.collection.mutable.ArrayBuffer

/**
 * Defines an effect.
 * Methods must be defined in class.
 */
trait IEffect{
  def apply(target: IUnitCard):Unit
  def setCard(C:IUnitCard):Unit
  def getReference:Option[Ref]
}
