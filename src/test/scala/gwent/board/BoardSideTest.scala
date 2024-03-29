package cl.uchile.dcc
package gwent.board

import gwent.board.*
import gwent.cards
import gwent.cards.*
import gwent.player.*

import munit.FunSuite

import scala.collection.mutable.ArrayBuffer

class BoardSideTest extends FunSuite{
  var Card1: CloseCombat = _
  var Card2: cards.Range = _
  var Card3: Siege = _

  var Card4: CloseCombat = _
  var Card5: cards.Range = _
  var Card6: Siege = _

  var Side: BoardSide = _
  var Board:Board =_

  var Deck:ArrayBuffer[ICard] = _

  var Player1:Player = _
  override def beforeEach(context: BeforeEach): Unit = {
    Card1 = new CloseCombat("Star Platinum", 15)
    Card2 = new cards.Range("Hierophant Green", 10)
    Card3 = new Siege("Hermit Purple", 5)

    Card4 = new CloseCombat("The World", 14)
    Card5 = new cards.Range("Emperor", 10)
    Card6 = new Siege("Death Thirteen", 5)

    Deck = ArrayBuffer(Card1,Card2,Card3)

    Player1 = new Player("Jotaro",Deck)

    Side = new BoardSide("South")
    Board = new Board()

    Board.addPlayer(Player1," ")
    Player1.setBoardSide(Side)

  }

  test("A side must have a name"){
    assertEquals("South",Side.getName())
  }
  test("Two sides are equal if they have the same name"){
    val Austral_side = new BoardSide("South")
    val Northern_side = new BoardSide("North")

    assertEquals(Austral_side,Side)
    assertEquals(Austral_side.hashCode(),Side.hashCode())

    assertNotEquals(Northern_side,Side)

    assert(!Side.equals(new Board()))
  }
  test("A side has a zone for each type of UnitCard, and they can be updated") {
    val CCzone = ArrayBuffer(Card1)
    val Rzone = ArrayBuffer(Card2)
    val Szone = ArrayBuffer(Card3)

    Side.addToCCzone(Card1)
    Side.addToRangeZone(Card2)
    Side.addToSiegeZone(Card3)

    assertEquals(Side.getCCzone(), CCzone)
    assertEquals(Side.getRangeZone(), Rzone)
    assertEquals(Side.getSiegeZone(), Szone)
  }
  test("A side has a zone for each type of UnitCard, and they update when a card is placed"){
    val CCzone = ArrayBuffer(Card1)
    val Rzone = ArrayBuffer(Card2)
    val Szone = ArrayBuffer(Card3)

    Side.placeCard(Card1,Card1)
    Side.placeCard(Card2,Card2)
    Side.placeCard(Card3, Card3)

    assertEquals(Side.getCCzone(), CCzone)
    assertEquals(Side.getRangeZone(), Rzone)
    assertEquals(Side.getSiegeZone(), Szone)

    Side.placeCard(Card4,Card4)
    Side.placeCard(Card5,Card5)
    Side.placeCard(Card6,Card6)

    assertNotEquals(Side.getCCzone(), CCzone)
    assertNotEquals(Side.getRangeZone(), Rzone)
    assertNotEquals(Side.getSiegeZone(), Szone)
  }
  test("A board side should be able to properly count its total score"){
    var score:Int = 0

    assertEquals(score,Side.getPoints())

    Side.placeCard(Card1,Card1)
    score = 15

    assertEquals(score,Side.getPoints())

    Side.placeCard(Card2,Card2)
    score = 25

    assertEquals(score, Side.getPoints())
  }
  test("A board side can receive commands from unit cards"){
    val CCzone = ArrayBuffer(Card1)
    val Rzone = ArrayBuffer(Card2)
    val Szone = ArrayBuffer(Card3)

    Card1.sendCommand(Player1)
    Card2.sendCommand(Player1)
    Card3.sendCommand(Player1)

    assertEquals(Side.getCCzone(),CCzone)
    assertEquals(Side.getRangeZone(), Rzone)
    assertEquals(Side.getSiegeZone(), Szone)
  }
  test("A player can send commands to its board side") {
    val CCzone = ArrayBuffer(Card1)
    val Rzone = ArrayBuffer(Card2)
    val Szone = ArrayBuffer(Card3)

    Player1.playCard(Card1)
    Player1.playCard(Card2)
    Player1.playCard(Card3)

    assertEquals(Side.getCCzone(),CCzone)
    assertEquals(Side.getRangeZone(),Rzone)
    assertEquals(Side.getSiegeZone(),Szone)
  }


}
