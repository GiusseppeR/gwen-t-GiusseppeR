package cl.uchile.dcc
package gwent

trait Iplayer {
  def getName: String
  def remainingJewels: Int
  def currentHand: Set[Card]
  def playCard(C:Card): Unit
  def takeCard(n:Int):Unit

  /*def boardSide:*/
}

class Player {

}
