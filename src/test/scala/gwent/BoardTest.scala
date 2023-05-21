package cl.uchile.dcc
package gwent

import munit.FunSuite

import scala.collection.mutable.ArrayBuffer

class BoardTest extends FunSuite {
  var Card1: ICard = _
  var Card2: ICard = _
  var Card3: ICard = _
  var Card4: ICard = _

  var Card5: ICard = _
  var Card6: ICard = _
  var Card7: ICard = _
  var Card8: ICard = _

  var Deck1:ArrayBuffer[ICard] = _
  var Deck2:ArrayBuffer[ICard] = _

  var Player1: Player = _
  var Player2: Player = _

  var Board: Board = _

  override def beforeEach(context:BeforeEach): Unit = {
    Card1 = new CloseCombat("Star Platinum", 15)
    Card2 = new Range("Hierophant Green", 10)
    Card3 = new Siege("Hermit Purple", 5)
    Card4 = new WeatherCard("Magician Red")

    Card5 = new CloseCombat("The World",14)
    Card6 = new Range("Emperor", 10)
    Card7 = new Siege("Death Thirteen", 5)
    Card8 = new WeatherCard("Sun")

    Deck1 = ArrayBuffer(Card1,Card2,Card3,Card4)
    Deck2 = ArrayBuffer(Card5,Card6,Card7,Card8)

    Player1 = new Player("Jotaro", Deck1)
    Player2 = new Player("DIO", Deck2)

    Board = new Board(Player1, Player2)
  }
  test("A board should have two players"){
    assertEquals(Player1, Board.getP1())
    assertEquals(Player2, Board.getP2())
  }
  test("The board should assign a side to each player"){
    val side1 = BoardSide("South")
    val side2 = BoardSide("North")

    assertEquals(side1,Player1.getBoardSide())
    assertEquals(side2,Player2.getBoardSide())
  }

  test("When a weather card is placed, it should be added to the 'Weather zone' and replace the current one"){
    Board.placeCard(Card3)
    assertEquals(Card3, Board.getCurrentWeatherCard())

    Board.placeCard(Card8)
    assertEquals(Card8, Board.getCurrentWeatherCard())
  }
  test("The Board can receive commands"){
    Side.receiveCommand(new playCommand(Card3))
    assertEquals(Card3, Board.getCurrentWeatherCard())
  }
  test("The players can send commands to the board"){
    Player1.playCard(Card3)
    assertEquals(Card3, Board.getCurrentWeatherCard())

    Player2.playCard(Card8)
    assertEquals(Card8, Board.getCurrentWeatherCard())
  }

  test("The board should be able to properly tell the winner of the round"){
    Player1.playCard(Card1)
    Player1.playCard(Card2)
    Player1.playCard(Card3)

    Player2.playCard(Card5)
    Player2.playCard(Card6)
    Player2.playCard(Card7)

    assertEquals(Player1,Board.getWinner())
  }
  test("Boards are unique"){
    val HearthstoneBoard = new Board(Player1,Player2)

    assertNotEquals(HearthstoneBoard,Board)
  }
}
