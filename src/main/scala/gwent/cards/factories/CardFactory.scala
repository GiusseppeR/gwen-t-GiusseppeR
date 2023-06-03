package cl.uchile.dcc
package gwent.cards.factories

import gwent.cards.*

trait CardFactory[T <: ICard] {
  def createCard(): T
}
