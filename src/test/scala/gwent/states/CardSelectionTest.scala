import cl.uchile.dcc.gwent
import cl.uchile.dcc.gwent.controller.Controller
import cl.uchile.dcc.gwent.states.InvalidTransitionException
import cl.uchile.dcc.gwent.states.controller.*
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
    Controller.selectCard()
    val User = Controller.User().get
    val card = User.currentHand()(6)

    CardSelection.playCard(5)

    assertEquals(User.currentHand().length,9)
    assertEquals(card,User.currentHand()(5))
  }
}
