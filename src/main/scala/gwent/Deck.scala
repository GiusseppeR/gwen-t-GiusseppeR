package cl.uchile.dcc
package gwent

trait IDeck{
  def makeHand():Set[Card]
  def AddCard(Hand:Set[Card]):Unit
}

class Deck {

}
