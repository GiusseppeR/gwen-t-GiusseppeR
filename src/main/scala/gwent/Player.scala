package cl.uchile.dcc
package gwent

import scala.collection.mutable.ArrayBuffer

trait Iplayer {
  def getName(): String
  def getDeck(): ArrayBuffer[Card]
  def shuffleDeck():Unit
  def remainingGems(): Int
  def takeDamage():Unit
  def currentHand(): ArrayBuffer[Card]
  def playCard(C:Card): Unit
  def takeCard(n:Int):Unit
}

class Player(private val name:String, private var deck:ArrayBuffer[Card]) extends Iplayer {
  def getName() = ""
  def getDeck() = ArrayBuffer(new CloseCombat("",0),new CloseCombat("",0))
  def shuffleDeck(): Unit = {

  }
  def remainingGems() = -100
  def takeDamage() = {

  }
  def currentHand() = deck
  def playCard(C:Card) = {

  }
  def takeCard(n:Int) = {

  }
}
