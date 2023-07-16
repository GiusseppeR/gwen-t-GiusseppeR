package cl.uchile.dcc
package gwent.effects

import munit.FunSuite
import gwent.cards.*
import gwent.cards.ref.*
import gwent.player.*
import gwent.board.*

import cl.uchile.dcc.gwent.effects.unit.MoraleBoostEffect

import scala.collection.mutable.ArrayBuffer

class MoraleBoostTest extends FunSuite{
  var Card1: CloseCombat = _
  var Card2: CloseCombat = _
  var Card3: CloseCombat = _
  var Card4: Range = _
  var Card5: Range = _

  var Board: Board = _
  var Player: Player = _

  override def beforeEach(context: BeforeEach): Unit = {
    Card1 = new CloseCombat("Imperator", 10, new MoraleBoostEffect())
    Card2 = new CloseCombat("Legionary", 5)
    Card3 = new CloseCombat("Centurion", 20)

    Card4 = new Range("Sagittarii", 10, new MoraleBoostEffect(CloseCombatRef))
    Card5 = new Range("Sagittarii", 10)

    Board = new Board()
    Player = new Player("Julius Caesar", ArrayBuffer(Card1, Card2, Card3, Card4,Card5))
    Board.addPlayer(Player, "North")
  }
  test("The default effect gets properly applied, only to the cards of its file"){
    Player.playCard(Card2)
    Player.playCard(Card3)
    Player.playCard(Card5)

    Player.playCard(Card1)

    assertEquals(Card1.getSP(), 10)
    assertEquals(Card2.getSP(), 6)
    assertEquals(Card3.getSP(), 21)
    assertEquals(Card5.getSP(), 10)
  }
  test("The effect gets properly applied when a reference is included") {
    Player.playCard(Card5)
    Player.playCard(Card1)
    Player.playCard(Card2)
    Player.playCard(Card4)

    assertEquals(Card5.getSP(),10)
    assertEquals(Card4.getSP(),10)
    assertEquals(Card1.getSP(), 11)
    assertEquals(Card2.getSP(),6)
  }

}
