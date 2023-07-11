package cl.uchile.dcc
package gwent.cards.pool

import gwent.cards.*

trait IPoolOfCards {
  def createCardPool():List[ICard]
  def addToPool(C:ICard, n:Int):Unit
  def removeFromPool(C:ICard, n:Int = 0):Unit
}
