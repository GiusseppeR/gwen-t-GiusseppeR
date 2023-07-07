package cl.uchile.dcc
package gwent.controller

import gwent.board.*
import gwent.cards.*
import gwent.controller.*
import gwent.player.*
import gwent.states.*
import gwent.states.controller.*

import munit.FunSuite

class ControllerStateTransitionTest extends FunSuite {
  var Controller:Controller = _
  override def beforeEach(context: BeforeEach): Unit = {
    Controller = new Controller()
  }
  test("The controller states can transition as intended"){
    assert(Controller.getState().isInstanceOf[MainMenu])

    Controller.newGame()
    assert(Controller.getState().isInstanceOf[GameConfiguration])

    Controller.goToMenu()
    assert(Controller.getState().isInstanceOf[MainMenu])

    Controller.newGame()
    Controller.startGame()
    assert(Controller.getState().isInstanceOf[GameStart])

    Controller.prepareRound()
    assert(Controller.getState().isInstanceOf[RoundStart])

    Controller.startRound()
    assert(Controller.getState().isInstanceOf[Idle])

    Controller.Pass()
    assert(Controller.getState().isInstanceOf[EndOfRound])

    Controller.nextRound()
    assert(Controller.getState().isInstanceOf[EndOfGame] ||
      Controller.getState().isInstanceOf[RoundStart] )

    Controller.setState(new EndOfGame(Controller))
    Controller.startRound()
    assert(Controller.getState().isInstanceOf[MainMenu])

    Controller.setState(new RoundStart(Controller))
    Controller.startRound()
    assert(Controller.getState().isInstanceOf[Idle])
  }
}
