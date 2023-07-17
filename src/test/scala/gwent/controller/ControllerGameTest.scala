package cl.uchile.dcc
package gwent.controller

import cl.uchile.dcc.gwent.states.*
import munit.FunSuite

class ControllerGameTest extends FunSuite{
  var Controller: Controller = _
  override def beforeEach(context: BeforeEach): Unit = {
    Controller = new Controller()
    Controller.newGame()
    Controller.startGame()
  }
  test("In the game, the user can choose to select a card or pass"){
    Controller.selectCard()
    assert(Controller.getState().isInstanceOf[CardSelection])
    Controller.setState(new Idle(Controller))
    Controller.Pass()
    assert(Controller.getState().isInstanceOf[EndOfRound])
  }
  test("The user is able to play a card") {
    Controller.selectCard()
    val User = Controller.User().get
    val card = User.currentHand()(5)
    var cardAmount: Int = 0
    var newCardAmount: Int = 0

    for (Card <- User.currentHand()) {
      if card == Card then cardAmount = cardAmount + 1
    }

    Controller.PlayCard(5)

    for (Card <- User.currentHand()) {
      if card == Card then newCardAmount = newCardAmount + 1
    }

    assertEquals(User.currentHand().length, 9)
    assertEquals(newCardAmount + 1, cardAmount)

    assert(Controller.getState().isInstanceOf[Idle])
  }
}
