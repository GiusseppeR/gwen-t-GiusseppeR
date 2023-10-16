package cl.uchile.dcc
package gwent.states

import cl.uchile.dcc.gwent
import cl.uchile.dcc.gwent.controller.Controller
import cl.uchile.dcc.gwent.states.{ControllerState, InvalidFunctionException, InvalidTransitionException}
import cl.uchile.dcc.gwent.states.*
import munit.FunSuite

class ControllerStateTest extends FunSuite{
  var Controller:Controller = _
  var ControllerState:ControllerState = _

  override def beforeEach(context: BeforeEach): Unit = {
    Controller = new Controller()
    ControllerState = new ControllerState(Controller)
  }
  test("The ControllerState State can transition as intended"){
    intercept[InvalidTransitionException]{
      ControllerState.toIdle()
    }
    intercept[InvalidTransitionException]{
      ControllerState.toEOR()
    }
    intercept[InvalidTransitionException] {
      ControllerState.toGameConfiguration()
    }
    intercept[InvalidTransitionException] {
      ControllerState.toMainMenu()
    }
    intercept[InvalidTransitionException] {
      ControllerState.toPassed()
    }
    intercept[InvalidTransitionException] {
      ControllerState.toCardSelection()
    }
  }
  test("All functions throw exceptions"){
    intercept[InvalidFunctionException] {
      ControllerState.transition()
    }
    intercept[InvalidFunctionException] {
      ControllerState.finishRound()
    }
    intercept[InvalidFunctionException] {
      ControllerState.pass()
    }
    intercept[InvalidFunctionException] {
      ControllerState.gameStartSettings
    }
    intercept[InvalidFunctionException] {
      ControllerState.playCard(2)
    }
    intercept[InvalidFunctionException] {
      ControllerState.setEnemy("A")
    }
    intercept[InvalidFunctionException] {
      ControllerState.setNumberOfRandomEnemies(1)
    }
  }
}
