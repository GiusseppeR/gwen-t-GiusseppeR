package cl.uchile.dcc
package gwent

trait IBoard {
  def placeCard(C: ICard):Unit
  def receiveCommand(e:ICommand):Unit
}
