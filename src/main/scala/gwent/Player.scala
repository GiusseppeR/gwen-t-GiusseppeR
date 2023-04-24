package cl.uchile.dcc
package gwent

import java.util.Objects
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

trait Iplayer {
  def getName(): String
  def getDeck(): ArrayBuffer[Card]
  def remainingGems(): Int
  def takeDamage():Unit
  def currentHand(): ArrayBuffer[Card]
  def playCard(C:Card): Unit
  def takeCard(n:Int):Unit
}

class Player(private val name:String, private var deck:ArrayBuffer[Card]) extends Iplayer {
  private var Gems: Int = 2
  private var Hand: ArrayBuffer[Card] = ArrayBuffer()

  override def getName(): String = name

  override def getDeck(): ArrayBuffer[Card] = deck

  private def shuffleDeck(): Unit = {
    Random.shuffle(deck)
  }

  override def remainingGems(): Int = Gems

  override def takeDamage(): Unit = {
    Gems = Gems - 1
  }

  override def currentHand(): ArrayBuffer[Card] = Hand

  def playCard(C: Card): Unit = {
    this.currentHand() -= C
  }

  def takeCard(n: Int): Unit = {
    val handSize:Int = this.currentHand().length
    val deckLast = this.getDeck().length-1
    var slice: ArrayBuffer[Card] = ArrayBuffer()
    var k:Int = n
    if( n > deckLast+1 ){
      k = deckLast+1-n
    }
    if(k+handSize > 10){
      k = 10-handSize
    }
    if (deckLast == 0 && k !=0){
      slice = getDeck()
    } else {
      slice = deck.slice(deckLast - k, deckLast)
    }
    this.currentHand() ++= slice
    this.getDeck() --= slice
  }

  override def equals(obj: Any): Boolean = {
    if (obj.isInstanceOf[Player]) {
      val other = obj.asInstanceOf[Player]
      (this eq other)
    } else {
      false
    }
  }

  override def hashCode(): Int = {
    Objects.hash(classOf[Player])
  }
  this.shuffleDeck()
  this.takeCard(10)

}
