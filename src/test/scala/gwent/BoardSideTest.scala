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

  var Deck: ArrayBuffer[ICard] = _

  var Player: Player = _

  var Side: BoardSide = _
  override def beforeEach(context: BeforeEach): Unit = {
    Card1 = new CloseCombat("Star Platinum", 15)
    Card2 = new Range("Hierophant Green", 10)
    Card3 = new Siege("Hermit Purple", 5)

    Card4 = new CloseCombat("The World", 14)
    Card5 = new Range("Emperor", 10)
    Card6 = new Siege("Death Thirteen", 5)

    Deck = ArrayBuffer(Card1,Card2,Card3,Card4,Card5,Card6)

    Player = new Player("Jotaro", Deck)

    Side = new BoardSide("South")

    Player.setBoardSide() = Side
  }

  test("A side must have a name"){
    assertEquals("South",Side.getName())
  }
  test("Two sides are equal if they have the same name"){
    val Austral_side = new BoardSide("South")

    assertEquals(Austral_side,Side)
  }
  test("A side has a zone for each type of UnitCard, which update when the player plays a card"){
    var CCzone = ArrayBuffer(Card1)
    var Rzone = ArrayBuffer(Card2)
    var Szone = ArrayBuffer(Card3)

    Player.playCard(Card1)
    Player.playCard(Card2)
    Player.playCard(Card3)

    assertEquals(CCzone,Side.getCCzone())
    assertEquals(Rzone,Side.getRangeZone())
    asssertEquals(Szone,Side.getSiegeZone())

    Player.playCard(Card4)
    Player.playCard(Card5)
    Player.playCard(Card6)

    assertNotEquals(CCzone, Side.getCCzone())
    assertNotEquals(Rzone, Side.getRangeZone())
    asssertNotEquals(Szone, Side.getSiegeZone())
  }
  test("A board side should be able to properly count the player's score"){
    var score:Int = 0

    assertEquals(score,Side.getPoints())

    Player.playCard(Card1)
    score = 15

    assertEquals(score,Side.getPoints())
  }
}
