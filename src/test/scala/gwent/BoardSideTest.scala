package cl.uchile.dcc
package gwent

import munit.FunSuite

import scala.collection.mutable.ArrayBuffer

class BoardSideTest extends FunSuite{
  var Card1: ICard = _
  var Card2: ICard = _
  var Card3: ICard = _

  var Card4: ICard = _
  var Card5: ICard = _
  var Card6: ICard = _

  var Side: BoardSide = _
  override def beforeEach(context: BeforeEach): Unit = {
    Card1 = new CloseCombat("Star Platinum", 15)
    Card2 = new Range("Hierophant Green", 10)
    Card3 = new Siege("Hermit Purple", 5)

    Card4 = new CloseCombat("The World", 14)
    Card5 = new Range("Emperor", 10)
    Card6 = new Siege("Death Thirteen", 5)
  }

  test("A side must have a name"){
    assertEquals("South",Side.getName())
  }
  test("Two sides are equal if they have the same name"){
    val Austral_side = new BoardSide("South")

    assertEquals(Austral_side,Side)
  }
  test("A side has a zone for each type of UnitCard, which update when the player plays a card"){
    val CCzone = ArrayBuffer(Card1)
    val Rzone = ArrayBuffer(Card2)
    val Szone = ArrayBuffer(Card3)

    Side.placeCard(Card1)
    Side.placeCard(Card2)
    Side.placeCard(Card3)

    assertEquals(CCzone,Side.getCCzone())
    assertEquals(Rzone,Side.getRangeZone())
    asssertEquals(Szone,Side.getSiegeZone())

    Side.placeCard(Card4)
    Side.placeCard(Card5)
    Side.placeCard(Card6)

    assertNotEquals(CCzone, Side.getCCzone())
    assertNotEquals(Rzone, Side.getRangeZone())
    asssertNotEquals(Szone, Side.getSiegeZone())
  }
  test("A board side should be able to properly count its total score"){
    var score:Int = 0

    assertEquals(score,Side.getPoints())

    Side.placeCard(Card1)
    score = 15

    assertEquals(score,Side.getPoints())

    Side.placeCard(Card2)
    score = 25

    assertEquals(score, Side.getPoints())
  }
  test("A board side can receive commands"){
    val CCzone = ArrayBuffer(Card1)

    Side.receiveCommand(new playCommand(Card1))
    assertEquals(CCzone, Side.getCCzone())
  }
  test("A player can send commands to the board side"){
    var Deck = ArrayBuffer(Card1,Card2,Card3)
    var Player = new Player("Jotaro",Deck)
    Player.setBoardSide() = Side

    val CCzone = ArrayBuffer(Card1)
    val Rzone = ArrayBuffer(Card2)
    val Szone = ArrayBuffer(Card3)

    Player.playCard(Card1)
    Player.playCard(Card2)
    Playe.playCard(Card3)

    assertEquals(CCzone, Side.getCCzone())
    assertEquals(Rzone, Side.getRangeZone())
    asssertEquals(Szone, Side.getSiegeZone())
  }
}
