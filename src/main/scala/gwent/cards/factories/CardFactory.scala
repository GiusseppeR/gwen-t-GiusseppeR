package cl.uchile.dcc
package gwent.cards.factories

import gwent.effects.IEffect
import gwent.cards.*

/** Defines a generic card factory
 *
 * Methods must be defined in class.
 *
 * @tparam T Type of the card to be produced. Covariant.
 */
trait CardFactory[+T <: ICard] {
  def setName(name:String):Unit
  def setEffect(effect:IEffect):Unit
  def setSP(n:Int):Unit
  def createCard():T
}
