package cl.uchile.dcc
package gwent

trait Iplayer {
  def getName(): String
  def remainingJewels(): Int
  def currentHand(): Set[Card]
  def playCard(): Unit
  def getCard():Unit

  /**def boardSide():*/
}

class Player {

}
