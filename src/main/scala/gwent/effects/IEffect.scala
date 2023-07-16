package cl.uchile.dcc
package gwent.effects

import gwent.cards.*

import scala.collection.mutable.ArrayBuffer

trait IEffect{
  def apply(target: IUnitCard):Unit
  def setCard(C:IUnitCard):Unit
  def getReference:Option[IUnitCard]
}
