package cl.uchile.dcc
package gwent.effects

import cl.uchile.dcc.gwent.cards.*
import cl.uchile.dcc.gwent.cards.ref.*
import gwent.board.*
import gwent.player.*
import gwent.effects.unit.*
import gwent.effects.weather.*
import scala.collection.mutable.ArrayBuffer
import munit.FunSuite

class WeatherEffectTest extends FunSuite {
  var Card1: CloseCombat = _
  var Card2: CloseCombat = _

  var Card3: Range = _
  var Card4: Range = _

  var Card5: Siege = _
  var Card6: Siege = _

  var Card7: Range = _

  var bitingFrost: WeatherCard = _
  var impenetrableFog: WeatherCard = _
  var torrentialRain: WeatherCard = _
  var clearWeather: WeatherCard = _

  var Deck: ArrayBuffer[ICard] = _
  var Board: Board = _
  var Player: Player = _

  override def beforeEach(context: BeforeEach): Unit = {
    Card1 = new CloseCombat("Infantry", 10, new MoraleBoostEffect())
    Card2 = new CloseCombat("Grenadier", 20)

    Card3 = new Range("Close Air Support", 10)
    Card4 = new Range("Tactical Bomber", 10)

    Card5 = new Siege("Strategic Bomber", 10)
    Card6 = new Siege("Rocket", 30)

    Card7 = new Range("Close Air Support", 10, new TightBondEffect())

    bitingFrost = new WeatherCard("Winter", new WeatherEffect(CloseCombatRef))
    impenetrableFog = new WeatherCard("Autumn", new WeatherEffect(RangeRef))
    torrentialRain = new WeatherCard("Summer", new WeatherEffect(SiegeRef))
    clearWeather = new WeatherCard("Spring", new ClearWeatherEffect())

    Board = new Board()
    Deck = ArrayBuffer(Card1, Card2, Card3, Card4, Card5, Card6, Card7, bitingFrost, impenetrableFog, torrentialRain)
    Player = new Player("Wiston Churchill", Deck)
    Board.addPlayer(Player, "Great Britain")

    Player.playCard(Card2)
    Player.playCard(Card1)

    Player.playCard(Card3)
    Player.playCard(Card4)

    Player.playCard(Card5)
    Player.playCard(Card6)
  }

  test("When active, the Weather Effects set the SP of the cards in the referenced type to 1") {
    Player.playCard(bitingFrost)

    var CCZone = Player.getBoardSide().getCCzone()
    var RangeZone = Player.getBoardSide().getRangeZone()
    var SiegeZone = Player.getBoardSide().getSiegeZone()

    assertEquals(1, CCZone(1).getSP())
    assertEquals(1, CCZone(0).getSP())

    assertEquals(10, RangeZone(0).getSP())
    assertEquals(10, RangeZone(1).getSP())

    assertEquals(10, SiegeZone(0).getSP())
    assertEquals(30, SiegeZone(1).getSP())

    Player.playCard(impenetrableFog)

    CCZone = Player.getBoardSide().getCCzone()
    RangeZone = Player.getBoardSide().getRangeZone()
    SiegeZone = Player.getBoardSide().getSiegeZone()

    assertEquals(10, CCZone(1).getSP())
    assertEquals(21, CCZone(0).getSP())

    assertEquals(1, RangeZone(0).getSP())
    assertEquals(1, RangeZone(1).getSP())

    assertEquals(10, SiegeZone(0).getSP())
    assertEquals(30, SiegeZone(1).getSP())

    Player.playCard(torrentialRain)

    CCZone = Player.getBoardSide().getCCzone()
    RangeZone = Player.getBoardSide().getRangeZone()
    SiegeZone = Player.getBoardSide().getSiegeZone()

    assertEquals(10, CCZone(1).getSP())
    assertEquals(21, CCZone(0).getSP())

    assertEquals(10, RangeZone(0).getSP())
    assertEquals(10, RangeZone(1).getSP())

    assertEquals(1, SiegeZone(0).getSP())
    assertEquals(1, SiegeZone(1).getSP())
  }
  test("Whenever a weather effect is active on a file, the new cards added to that file also get the effect") {
    Player.playCard(impenetrableFog)
    Player.playCard(Card7)

    val RangeZone = Player.getBoardSide().getRangeZone()

    assertEquals(1, RangeZone(0).getSP())
    assertEquals(1, RangeZone(1).getSP())
    assertEquals(1, RangeZone(2).getSP())
  }
}

