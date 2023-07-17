import cl.uchile.dcc.gwent
import cl.uchile.dcc.gwent.controller.Controller
import cl.uchile.dcc.gwent.states.{EndOfRound, Idle, InvalidTransitionException, Passed}
import cl.uchile.dcc.gwent.states.*
import munit.FunSuite

class PassedTest extends FunSuite{
  var Controller:Controller = _
  var Passed:Passed = _

  override def beforeEach(context: BeforeEach): Unit = {
    Controller = new Controller()
    Passed = new Passed(Controller)

    Controller.newGame()
    Controller.numberOfRandomEnemies(3)
    Controller.startGame()
  }
  test("The Passed State can transition as intended"){
    intercept[InvalidTransitionException]{
      Passed.toPassed()
    }
    intercept[InvalidTransitionException] {
      Passed.toCardSelection()
    }
    intercept[InvalidTransitionException] {
      Passed.toMainMenu()
    }
    intercept[InvalidTransitionException] {
      Passed.toGameConfiguration()
    }

    intercept[InvalidTransitionException] {
      Passed.toIdle()
    }

    Controller.setState(Passed)
    Passed.toEOR()
    assert(Controller.getState().isInstanceOf[EndOfRound])
  }
  test("The Pass state iterates until all players have passed"){
    Passed.pass()

    assertEquals(Controller.getPassedPlayerNames.length, 4)
  }
}