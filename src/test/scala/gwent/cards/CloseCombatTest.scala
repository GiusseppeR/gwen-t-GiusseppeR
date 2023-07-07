package cl.uchile.dcc
package gwent.cards

import gwent.board.*
import gwent.cards.*
import gwent.player.*

import munit.FunSuite

import scala.collection.mutable.ArrayBuffer

class CloseCombatTest extends FunSuite {
  var Card1:CloseCombat = _
  var Card2:CloseCombat = _

  var Board: Board =_
  var Player:Player =_
  override def beforeEach(context:BeforeEach):Unit = {
    Card1 = new CloseCombat("The Emperor", 12)
    Card2 = new CloseCombat("The Chariot", 5)

    Board = new Board()
    Player = new Player("Polnareff", ArrayBuffer(Card1,Card2))

    Board.addPlayer(Player,"North")
  }
  test("A CC card should have a name and a strength number associated"){
    assertEquals(Card1.getName(), "The Emperor")
    assertEquals(Card1.getSP(), 12)

    assertEquals(Card2.getName(), "The Chariot")
    assertEquals(Card2.getSP(), 5)
  }
  test("Two CC cards are equivalent if they have the same name and number of strength points"){
    val Emperor_2: CloseCombat = new CloseCombat("The Emperor", 12)

    assertEquals(Card1, Emperor_2)
    assertEquals(Card1.hashCode(),Emperor_2.hashCode())
    assert(!Card1.equals(Card2))
    assert(!Card1.equals(new WeatherCard("test")))
  }
  test("Cards know their place in the board"){
    val CCzone = ArrayBuffer(Card1)
    Card1.goToZone(Player.getBoardSide())

    assertEquals(CCzone, Player.getBoardSide().getCCzone())
  }
}

