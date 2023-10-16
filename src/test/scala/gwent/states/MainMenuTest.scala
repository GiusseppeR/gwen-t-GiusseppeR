package cl.uchile.dcc
package gwent.states

import munit.FunSuite
import gwent.states.*
import gwent.controller.*

class MainMenuTest extends FunSuite{
  var Controller:Controller = _
  var MainMenu:MainMenu = _

  override def beforeEach(context: BeforeEach): Unit = {
    Controller = new Controller()
    MainMenu = new MainMenu(Controller)
  }
  test("The Main Menu State can transition as intended"){
    intercept[InvalidTransitionException]{
      MainMenu.toIdle()
    }

    intercept[InvalidTransitionException]{
      MainMenu.toEOR()
    }

    intercept[InvalidTransitionException] {
      MainMenu.toPassed()
    }
    intercept[InvalidTransitionException] {
      MainMenu.toCardSelection()
    }

    MainMenu.toGameConfiguration()
    assert( Controller.getState().isInstanceOf[GameConfiguration] )

  }
}
