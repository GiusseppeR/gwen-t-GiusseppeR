package cl.uchile.dcc
package gwent.cards

import gwent.board.*
import gwent.cards.*
import gwent.player.*

trait IUnitCard extends AbstractCard{
  def getSP():Int
  def goToZone(B: BoardSide):Unit
}
