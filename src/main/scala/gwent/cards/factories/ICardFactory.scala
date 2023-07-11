package cl.uchile.dcc
package gwent.cards.factories

import gwent.cards.*

trait ICardFactory{
  def setCard(C:ICard):Unit
  def createCard():ICard
}
