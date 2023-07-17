package cl.uchile.dcc
package gwent.states

import cl.uchile.dcc.gwent
import cl.uchile.dcc.gwent.controller.Controller
import cl.uchile.dcc.gwent.states.{CardSelection, Idle, InvalidTransitionException}
import cl.uchile.dcc.gwent.states.*
import munit.FunSuite

class CardSelectionTest extends FunSuite{
  var Controller:Controller = _
  var CardSelection:CardSelection = _

  override def beforeEach(context: BeforeEach): Unit = {
    Controller = new Controller()
    CardSelection = new CardSelection(Controller)
  }
  test("The CardSelection State can transition as intended"){
    intercept[InvalidTransitionException]{
      CardSelection.toCardSelection()
    }
    intercept[InvalidTransitionException]{
      CardSelection.toEOR()
    }
    intercept[InvalidTransitionException] {
      CardSelection.toGameConfiguration()
    }
    intercept[InvalidTransitionException] {
      CardSelection.toMainMenu()
    }
    intercept[InvalidTransitionException] {
      CardSelection.toPassed()
    }
    Controller.setState(CardSelection)
    CardSelection.toIdle()
    assert( Controller.getState().isInstanceOf[Idle])
  }

  test("In the CardSelection state, the player can play a card"){
    Controller.newGame()
    Controller.startGame()
    Controller.selectCard()
    val User = Controller.User().get
    val card = User.currentHand()(5)
    var cardAmount:Int = 0
    var newCardAmount:Int = 0

    for(Card<-User.currentHand()){
      if card == Card then cardAmount= cardAmount + 1
    }

    CardSelection.playCard(5)
    for (Card <- User.currentHand()) {
      if card == Card then newCardAmount = newCardAmount + 1
    }

    assertEquals(User.currentHand().length,9)
    assertEquals(newCardAmount+1, cardAmount)
  }
}
