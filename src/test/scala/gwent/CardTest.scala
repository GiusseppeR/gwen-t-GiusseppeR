package cl.uchile.dcc
package gwent

import munit.FunSuite

class CardTest extends FunSuite {
  var Card1: Card = _
  var Card2: Card = _
  var Card3: Card = _

  override beforeEach(context:BeforeEach):Unit = {
    Card1 = new Card("The Emperor")
    Card2 = new Card("The Sun")
    Card3 = new Card("The Tower")
  }
  test("A card should have a name"){
    assertEquals(Card1.getName(), "The Emperor")

    assertEquals(Card2.getName(), "The Sun")

    assertEquals(Card3.getName(), "The Tower")
  }
  test("Two cards are equivalent if they have the same name"){
    val Emperor_2 = new Card("The Emperor")

    assertEquals(Card1, Emperor_2)
    assert(!Card1.equals(Card2))
    assert(!Card1.equals(Card3))
  }
}

