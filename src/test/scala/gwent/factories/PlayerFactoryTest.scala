package cl.uchile.dcc
package gwent.factories

import munit.FunSuite
import gwent.player.*
import gwent.player.factories.*
import gwent.cards.*

import scala.collection.mutable.ArrayBuffer

class PlayerFactoryTest extends FunSuite{
  var Factory: PlayerFactory = _

  var Card1: ICard = _
  var Card2: ICard = _
  var Card3: ICard = _
  var Card4: ICard = _
  var Card5: ICard = _
  var Card6: ICard = _
  var Card7: ICard = _
  var Card8: ICard = _
  var Card9: ICard = _
  var Card10: ICard = _
  var Card11: ICard = _

  var Deck1: ArrayBuffer[ICard] = _

  var Player1: Player = _
  override def beforeEach(context: BeforeEach): Unit = {
    Factory = new PlayerFactory()

    Card1 = new WeatherCard("1")
    Card2 = new Siege("2", 2)
    Card3 = new Siege("3", 3)
    Card4 = new CloseCombat("4", 4)
    Card5 = new CloseCombat("5", 5)
    Card6 = new Range("6", 6)
    Card7 = new Range("7", 7)
    Card8 = new WeatherCard("8")
    Card9 = new WeatherCard("9")
    Card10 = new WeatherCard("10")
    Card11 = new WeatherCard("11")

    Deck1 = ArrayBuffer(Card1, Card1, Card3, Card3, Card4, Card5, Card6, Card8, Card11, Card9, Card9)

    Player1 = new Player("P1", Deck1)
  }
  test("A factory can create a player"){
    Factory.setDeck(Deck1)
    Factory.setName("P1")

    val newPlayer = Factory.createPlayer()

    assertEquals(Player1.getName(), newPlayer.getName())
    assertEquals(Player1.getInitDeck(), newPlayer.getInitDeck())
  }
}
