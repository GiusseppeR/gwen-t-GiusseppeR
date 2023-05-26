package cl.uchile.dcc
package gwent

import gwent.board.*
import gwent.cards.*
import gwent.player.*
import munit.FunSuite
import scala.collection.mutable.ArrayBuffer
class WeatherTest extends FunSuite {
  var Card1: WeatherCard = _
  var Card2: WeatherCard = _

  var Board: Board = _
  var Player: Player = _
  override def beforeEach(context:BeforeEach):Unit = {
    Card1 = new WeatherCard("The Sun")
    Card2 = new WeatherCard("Judgement")

    Board = new Board()
    Player = new Player("Polnareff", ArrayBuffer(Card1, Card2))

    Board.addPlayer(Player, "North")
  }
  test("A Weather card should have a name"){
    assertEquals(Card1.getName(), "The Sun")
    assertEquals(Card2.getName(), "Judgement")
  }
  test("Two Weather cards are equivalent if they have the same name"){
    val Sun_2: WeatherCard = new WeatherCard("The Sun")

    assertEquals(Card1,Sun_2)
    assertEquals(Card1.hashCode(),Sun_2.hashCode())
    assert(!Card1.equals(Card2))
    assert(!Card1.equals(new CloseCombat("test", 0)))
  }
  test("Cards know their place in the board") {
    val WeatherZone: WeatherCard = Card1
    Card1.sendCommand(Player)

    assertEquals(WeatherZone, Player.getBoard().getCurrentWeatherCard())
  }
}

