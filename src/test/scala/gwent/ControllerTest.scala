package cl.uchile.dcc
package gwent

import gwent.board.*
import gwent.cards.*
import gwent.player.*
import gwent.controller.*

import cl.uchile.dcc.gwent.states.{EndOfGame, EndOfRound, GameConfiguration, GameStart, MainMenu, Passed, RoundStart}
import munit.FunSuite

class ControllerTest extends FunSuite {
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

    Controller.startRound()
    assert(Controller.getState().isInstanceOf[First])

    Controller.playCard()
    assert(Controller.getState().isInstanceOf[Second])

    Controller.playCard()
    assert(Controller.getState().isInstanceOf[First])

    Controller.Pass()
    assert(Controller.getState().isInstanceOf[Passed])

    Controller.Pass()
    assert(Controller.getState().isInstanceOf[EndOfRound])

    Controller.nextRound()
    assert(Controller.getState().isInstanceOf[EndOfGame] ||
      Controller.getState().isInstanceOf[RoundStart] )

    Controller.setState(new EndofGame)
    Controller.startRound()
    assert(Controller.getState().isInstanceOf[MainMenu])

    Controller.setState(new RoundStart() )
    Controller.startRound()
    assert(Controller.getState().isInstanceOf[First])

  }
}
