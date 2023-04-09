package cl.uchile.dcc
package gwent

import munit.FunSuite

class PlayerTest extends FunSuite {
  var Card1: Card = _
  var Card2: Card = _
  var Card3: Card = _
  var Card4: Card = _
  var Card5: Card = _
  var Card6: Card = _
  var Card7: Card = _
  var Card8: Card = _
  var Card9: Card = _
  var Card10: Card = _
  var Card11: Card = _

  var Deck1: Array[Card] =_
  var Deck2: Array[Card] =_

  var Player1: Player =_
  var Player2: Player =_

  override def beforeEach(context: BeforeEach): Unit = {
    Card1 = new Card("1")
    Card2 = new Card("2")
    Card3 = new Card("3")
    Card4 = new Card("4")
    Card5 = new Card("5")
    Card6 = new Card("6")
    Card7 = new Card("7")
    Card8 = new Card("8")
    Card9 = new Card("9")
    Card10 = new Card("10")
    Card11 = new Card("11")

    Deck1 = Array(Card1, Card1, Card3, Card3, Card4, Card5, Card6, Card8, Card11, Card9, Card9)
    Deck2 = Array(Card1, Card2, Card3, Card4, Card5, Card6, Card7, Card8, Card9, Card10, Card11)

    Player1 = new Player("Jotaro", Deck1)
    Player2 = new Player("D'Arby", Deck2)
  }
  test("Players should have a name"){
    assertEquals(Player1.getname(), "Jotaro")
    assertEquals(Player2.getname(), "D'Arby")
  }
  test("The deck size of the players should decrease by 10 when the class is created"){
    val expected = 1
    assertEquals(Player1.getDeckSize(), expected)
    assertEquals(Player2.getDeckSize(), expected)
  }
  test("The deck size of the players should decrease in size when they take cards") {
    val expected = 0
    Player1.takeCard(1)
    Player2.takeCard(1)
    assertEquals(Player1.getDeckSize(), expected)
    assertEquals(Player2.getDeckSize(), expected)
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
  test("Players shouldn't be able to take more cards than the amount available in the deck"){
    val expected = 9
    Player1.playCard()
    Player1.playCard()
    Player1.takeCard(3)
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


}
