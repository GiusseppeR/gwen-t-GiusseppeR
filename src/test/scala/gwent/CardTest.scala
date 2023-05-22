package cl.uchile.dcc
package gwent

import munit.FunSuite

import scala.collection.mutable.ArrayBuffer

class CloseCombatTest extends FunSuite {
  var Card1:CloseCombat = _
  var Card2:CloseCombat = _

  var Board: Board =_
  var Player:Player =_
  override def beforeEach(context:BeforeEach):Unit = {
    Card1 = new CloseCombat("The Emperor", 12)
    Card2 = new CloseCombat("The Chariot", 5)

    Board = new Board()
    Player = new Player("Polnareff", ArrayBuffer(Card1,Card2))

    Board.addPlayer(Player,"North")
  }
  test("A CC card should have a name and a strength number associated"){
    assertEquals(Card1.getName(), "The Emperor")
    assertEquals(Card1.getSP(), 12)

    assertEquals(Card2.getName(), "The Chariot")
    assertEquals(Card2.getSP(), 5)
  }
  test("Two CC cards are equivalent if they have the same name and number of strength points"){
    val Emperor_2: CloseCombat = new CloseCombat("The Emperor", 12)

    assertEquals(Card1, Emperor_2)
    assertEquals(Card1.hashCode(),Emperor_2.hashCode())
    assert(!Card1.equals(Card2))
    assert(!Card1.equals(new WeatherCard("test")))
  }
  test("Cards know their place in the board"){
    val CCzone = ArrayBuffer(Card1)
    Card1.goToZone(Player.getBoardSide())

    assertEquals(CCzone, Player.getBoardSide().getCCzone())
  }
}

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
    val Hierophant_2:Range = new Range("The Hierophant",6)

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
    Card1.goToZone(Player.getBoard())

    assertEquals(WeatherZone, Player.getBoard().getCurrentWeatherCard())
  }
}

