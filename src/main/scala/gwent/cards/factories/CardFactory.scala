package cl.uchile.dcc
package gwent.cards.factories

import gwent.effects.IEffect
import gwent.cards.*

trait CardFactory[+T <: ICard] {
  def setName(name:String):Unit
  def setEffect(effect:IEffect):Unit
  def setSP(n:Int):Unit
  def createCard():T
}
