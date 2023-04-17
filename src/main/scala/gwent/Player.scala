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
}

class Player(private val name:String, private var deck:Array[Card]) {
  def getName() = ""
  def getDeckSize() = 0
  def remainingGems() = 0
  def takeDamage() = {

  }
  def currentHand() = deck
  def playCard(C:Card) = {

  }
  def takeCard(n:Int) = {

  }
}
