package cl.uchile.dcc
package gwent

import gwent.board.*
import gwent.cards.*
import gwent.player.*
import munit.FunSuite

import scala.collection.mutable.ArrayBuffer

class SiegeTest extends FunSuite {
  var Card1: Siege = _
  var Card2: Siege = _

  var Board: Board = _
  var Player: Player = _
  override def beforeEach(context:BeforeEach):Unit = {
    Card1 = new Siege("The Tower", 10)
    Card2 = new Siege("Wheel of Fortune", 5)
    Board = new Board()
    Player = new Player("Polnareff", ArrayBuffer(Card1, Card2))

    Board.addPlayer(Player, "North")
  }
  test("A Siege card should have a name and a strength number associated"){
    assertEquals(Card1.getName(), "The Tower")
    assertEquals(Card1.getSP(), 10)

    assertEquals(Card2.getName(), "Wheel of Fortune")
    assertEquals(Card2.getSP(), 5)
  }
  test("Two Siege cards are equivalent if they have the same name and number of strength points"){
    val Tower_2:Siege = new Siege("The Tower",10)

    assertEquals(Card1, Tower_2)
    assertEquals(Card1.hashCode(),Tower_2.hashCode())
    assert(!Card1.equals(Card2))
    assert(!Card1.equals(new WeatherCard("test")))
  }
  test("Cards know their place in the board") {
    val SiegeZone= ArrayBuffer(Card1)
    Card1.goToZone(Player.getBoardSide())

    assertEquals(SiegeZone, Player.getBoardSide().getSiegeZone())
  }
}
