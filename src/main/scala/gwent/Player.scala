package cl.uchile.dcc
package gwent

trait Iplayer {
  def getName: String
  def getDeckSize: Int
  def remainingGems: Int
  def takeDamage:Unit
  def currentHand: Array[Card]
  def playCard(C:Card): Unit
  def takeCard(n:Int):Unit

  /*def boardSide:*/
}

class Player {

}
