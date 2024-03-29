package cl.uchile.dcc
package gwent.player

import gwent.board.*
import gwent.cards
import gwent.cards.*
import gwent.player.*

import cl.uchile.dcc.gwent.exceptions.NegativeAmountException
import munit.FunSuite

import scala.collection.mutable.ArrayBuffer

class PlayerTest extends FunSuite {
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

  var Deck1: ArrayBuffer[ICard] =_
  var Deck2: ArrayBuffer[ICard] =_

  var Player1: Player =_
  var Player2: Player =_

  var Board:Board =_

  var Side:BoardSide =_
  override def beforeEach(context: BeforeEach): Unit = {
    Card1 = new WeatherCard("1")
    Card2 = new Siege("2",2)
    Card3 = new Siege("3",3)
    Card4 = new CloseCombat("4",4)
    Card5 = new CloseCombat("5",5)
    Card6 = new cards.Range("6",6)
    Card7 = new cards.Range("7",7)
    Card8 = new WeatherCard("8")
    Card9 = new WeatherCard("9")
    Card10 = new WeatherCard("10")
    Card11 = new WeatherCard("11")

    Deck1 = ArrayBuffer(Card1, Card1, Card3, Card3, Card4, Card5, Card6, Card8, Card11, Card9, Card9)
    Deck2 = ArrayBuffer(Card1, Card2, Card3, Card4, Card5, Card6, Card7, Card8, Card9, Card11, Card11)

    Player1 = new Player("Jotaro", Deck1)
    Player2 = new Player("D'Arby", Deck2)

    Board = new Board()
    Side = new BoardSide("East")
  }
  test("Players should have a name and a starting deck"){
    assertEquals(Player1.getName(), "Jotaro")
    assertEquals(Player2.getName(), "D'Arby")

    assertEquals(Player1.getInitDeck(), Deck1)
    assertEquals(Player2.getInitDeck(),Deck2)
  }
  test("Players are identified by name"){
    val Player1_2: Player = new Player("Jotaro", Deck2)

    assert(!Player1.equals(Player2))
    assert(Player1.equals(Player1_2))
    assert(!Player1.equals(new WeatherCard("test")))
    assertEquals(Player1, Player1)

    assertEquals(Player1.hashCode(),Player1_2.hashCode())
  }
  test("Players can be linked to a board and a board side"){
    Player1.setBoard(Board)
    Player1.setBoardSide(Side)

    assertEquals(Board,Player1.getBoard())
    assertEquals(Side,Player1.getBoardSide())
  }
  test("The deck size of the players should decrease by 10 when the class is created"){
    val expected = 1
    assertEquals(Player1.getDeck().length, expected)
    assertEquals(Player2.getDeck().length, expected)
  }
  test("The starting hand of the players should have 10 cards"){
    val expected = 10
    assertEquals(Player1.currentHand().length, expected)
    assertEquals(Player2.currentHand().length, expected)
  }
  test("the hands of the players shouldn't have more than 10 cards") {
    val expected = 10
    Player1.takeCard(1)
    Player2.takeCard(1)
    assertEquals(Player1.currentHand().length, expected)
    assertEquals(Player2.currentHand().length, expected)
  }
  test("the hand size of the players should decrease by 1 when they play a card") {
    val expected = 9
    Player1.playCard(Card1)
    Player2.playCard(Card11)
    assertEquals(Player1.currentHand().length, expected)
    assertEquals(Player2.currentHand().length, expected)
  }
  test("The deck size of the players should decrease in size when they take cards") {
    val expected = 0

    Player1.takeCard(-1) //it does nothing

    assertEquals(Player1.getDeck().length, 1)

    Player1.playCard(Card1)
    Player2.playCard(Card11)
    Player1.takeCard(1)
    Player2.takeCard(1)
    assertEquals(Player1.getDeck().length, expected)
    assertEquals(Player2.getDeck().length, expected)

  }
  test("Players shouldn't be able to take more cards than the amount available in the deck"){
    val expected = 9
    Player1.playCard(Card1)
    Player1.playCard(Card3)
    Player1.takeCard(2)
    assertEquals(Player1.currentHand().length, expected)

  }
  test("Players should have 2 gems at the beginning of the game") {
    val expected = 2
    assertEquals(Player1.remainingGems(), expected)
    assertEquals(Player2.remainingGems(), expected)
  }
  test("Players should loose a gem when they take damage") {
    val expected = 1
    Player1.takeDamage()
    Player2.takeDamage()
    assertEquals(Player1.remainingGems(), expected)
    assertEquals(Player2.remainingGems(), expected)
  }
  test("The deck of the players should be able to properly shuffle"){
    /*disclaimer: the deck could, eventually, stay the same.
    * However, with 15 different cards, the probability of it staying the same is about 0.00000000007%
    * We can safely assume that it won't happen.*/

    val Card12 = new WeatherCard("12")
    val Card13 = new WeatherCard("13")
    val Card14 = new WeatherCard("14")
    val Card15 = new WeatherCard("15")
    val Rdeck = ArrayBuffer(Card1,Card2,Card3,Card4,Card5,Card6,Card7,Card8,Card9,Card10,Card11,Card12,Card13,Card14,Card15)

    val Player3 = new Player("test",Rdeck)
    Player3.shuffleDeck()

    assertNotEquals(Rdeck, Player3.getDeck() )
  }


}
