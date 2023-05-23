package cl.uchile.dcc
package gwent

trait IUnitCard extends AbstractCard{
  def getSP():Int
  def goToZone(B: BoardSide):Unit
}
