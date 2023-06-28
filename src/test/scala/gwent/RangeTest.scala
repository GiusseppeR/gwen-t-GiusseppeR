package cl.uchile.dcc
package gwent

import gwent.board.*
import gwent.cards.*
import gwent.player.*
import munit.FunSuite
import scala.collection.mutable.ArrayBuffer

class RangeTest extends FunSuite {
  var Card1: Range = _
  var Card2: Range = _

  var Board: Board = _
  var Player: Player = _
  override def beforeEach(context:BeforeEach):Unit = {
    Card1 = new Range("The Hierophant", 6)
    Card2 = new Range("The High Priestess", 6)

    Board = new Board()
    Player = new Player("Polnareff", ArrayBuffer(Card1, Card2))

    Board.addPlayer(Player, "North")
  }
  test("A Range card should have a name and a strength number associated"){
    assertEquals(Card1.getName(), "The Hierophant")
    assertEquals(Card1.getSP(), 6)

    assertEquals(Card2.getName(), "The High Priestess")
    assertEquals(Card2.getSP(), 6)
  }
  test("Two Range cards are equivalent if they have the same name and number of strength points"){
    val Hierophant_2:cards.Range = new cards.Range("The Hierophant",6)

    assertEquals(Card1, Hierophant_2)
    assertEquals(Card1.hashCode(),Hierophant_2.hashCode())
    assert(!Card1.equals(Card2))
    assert(!Card1.equals(new WeatherCard("test")))
  }
  test("Cards know their place in the board") {
    val RangeZone = ArrayBuffer(Card1)
    Card1.goToZone(Player.getBoardSide())

    assertEquals(RangeZone, Player.getBoardSide().getRangeZone())
  }
}
