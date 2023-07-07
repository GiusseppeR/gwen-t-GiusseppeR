package cl.uchile.dcc
package gwent.notifications

import cl.uchile.dcc.gwent.controller.*
import cl.uchile.dcc.gwent.player.Iplayer
import munit.FunSuite

class PlayerControllerTest extends FunSuite{
  var Controller:IController = _

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
  var Deck2: ArrayBuffer[ICard] = _

  var Player1: Player = _
  var Player2: Player = _

  var Board: Board = _

  override def beforeEach(context: BeforeEach): Unit = {
    Card1 = new WeatherCard("1")
    Card2 = new Siege("2", 2)
    Card3 = new Siege("3", 3)
    Card4 = new CloseCombat("4", 4)
    Card5 = new CloseCombat("5", 5)
    Card6 = new cards.Range("6", 6)
    Card7 = new cards.Range("7", 7)
    Card8 = new WeatherCard("8")
    Card9 = new WeatherCard("9")
    Card10 = new WeatherCard("10")
    Card11 = new WeatherCard("11")

    Deck1 = ArrayBuffer(Card1, Card1, Card3, Card3, Card4, Card5, Card6, Card8, Card11, Card9, Card9)
    Deck2 = ArrayBuffer(Card1, Card2, Card3, Card4, Card5, Card6, Card7, Card8, Card9, Card11, Card11)

    Player1 = new Player("Jotaro", Deck1)
    Player2 = new Player("D'Arby", Deck2)

    Board = new Board()
    Controller = new Controller()
  }
  test(""){

  }
}
