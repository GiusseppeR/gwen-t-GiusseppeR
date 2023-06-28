package cl.uchile.dcc
package gwent.effects

import gwent.cards.*

import scala.collection.mutable.ArrayBuffer

trait IEffect {
  def apply(card: ICard, target:IUnitCard):Unit
}
