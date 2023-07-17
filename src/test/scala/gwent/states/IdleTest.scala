package cl.uchile.dcc
package gwent.states

import munit.FunSuite
import gwent.states.controller.*
import gwent.controller.*

class IdleTest extends FunSuite{
  var Controller:Controller = _
  var Idle:Idle = _

  override def beforeEach(context: BeforeEach): Unit = {
    Controller = new Controller()
    Controller.newGame()
    Controller.startGame()
    Idle = new Idle(Controller)
  }
  test("The Idle State can transition as intended"){
    intercept[InvalidTransitionException]{
      Idle.toIdle()
    }

    intercept[InvalidTransitionException]{
      Idle.toEOR()
    }

    intercept[InvalidTransitionException] {
      Idle.toGameConfiguration()
    }
    intercept[InvalidTransitionException] {
      Idle.toMainMenu()
    }

    Idle.toCardSelection()
    assert( Controller.getState().isInstanceOf[CardSelection])
    Controller.setState(Idle)
    Idle.toPassed()
    assert(Controller.getState().isInstanceOf[Passed])
  }
  test("The Idle state allows the user to pass") {
    val user = Controller.User().get
    Idle.pass()
    assert(Controller.getPassedPlayerNames.contains(user.getName()))
  }
}