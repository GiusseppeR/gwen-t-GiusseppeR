package cl.uchile.dcc
package gwent.effects

import gwent.cards.*

import cl.uchile.dcc.gwent.board.Board
import cl.uchile.dcc.gwent.cards.ref.{CloseCombatRef, RangeRef, SiegeRef}
import cl.uchile.dcc.gwent.effects.unit.MoraleBoostEffect
import cl.uchile.dcc.gwent.effects.weather.{ClearWeatherEffect, WeatherEffect}
import cl.uchile.dcc.gwent.player.Player
import munit.FunSuite

import scala.collection.mutable.ArrayBuffer

class ClearWeatherTest extends FunSuite{
  var Card1: CloseCombat = _
  var Card2: CloseCombat = _

  var Card3: Range = _
  var Card4: Range = _

  var Card5: CloseCombat = _
  var Card6: CloseCombat = _

  var Card7: Range = _
  var Card8: Range = _

  var bitingFrost:WeatherCard =_
  var clearWeather:WeatherCard =_

  var Board: Board = _
  var Player2: Player = _
  var Player1: Player = _

  override def beforeEach(context: BeforeEach): Unit = {
    Card1 = new CloseCombat("Infantry", 10, new MoraleBoostEffect())
    Card2 = new CloseCombat("Grenadier", 20)

    Card3 = new Range("Close Air Support", 10)
    Card4 = new Range("Tactical Bomber", 10)

    Card5 = new CloseCombat("Infantry", 10, new MoraleBoostEffect())
    Card6 = new CloseCombat("Grenadier", 20)

    Card7 = new Range("Close Air Support", 10)
    Card8 = new Range("Tactical Bomber", 10)

    bitingFrost = new WeatherCard("Winter", new WeatherEffect(CloseCombatRef))
    clearWeather = new WeatherCard("Spring", new ClearWeatherEffect())

    Board = new Board()

    Player1 = new Player("Wiston Churchill", ArrayBuffer(Card1, Card2, Card3, Card4, bitingFrost, clearWeather))
    Board.addPlayer(Player1, "Great Britain")

    Player2 = new Player("Franklin Roosevelt", ArrayBuffer(Card5, Card6, Card7, Card8, bitingFrost, clearWeather))
    Board.addPlayer(Player2, "USA")

    Player1.playCard(Card2)
    Player1.playCard(Card1)

    Player1.playCard(Card3)
    Player1.playCard(Card4)

    Player2.playCard(Card6)
    Player2.playCard(Card5)

    Player2.playCard(Card7)
    Player2.playCard(Card8)
  }
  test("When the Clear Weather effect is active, sets the SP of the cards back to normal"){
    Player1.playCard(bitingFrost)

    Player2.playCard(clearWeather)

    val CC1 = Player1.getBoardSide().getCCzone()
    val Range1 = Player1.getBoardSide().getRangeZone()

    val CC2 = Player2.getBoardSide().getCCzone()
    val Range2 = Player2.getBoardSide().getRangeZone()

    assertEquals(10, CC1(1).getSP())
    assertEquals(21, CC1(0).getSP())

    assertEquals(10, Range1(0).getSP())
    assertEquals(10, Range1(1).getSP())

    assertEquals(10, CC2(1).getSP())
    assertEquals(21, CC2(0).getSP())

    assertEquals(10, Range2(0).getSP())
    assertEquals(10, Range2(1).getSP())
  }
}
