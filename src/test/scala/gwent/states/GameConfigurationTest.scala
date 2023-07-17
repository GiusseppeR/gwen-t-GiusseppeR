package cl.uchile.dcc
package gwent.states

import munit.FunSuite
import gwent.states.controller.*
import gwent.controller.*

import cl.uchile.dcc.gwent.board.Board

import scala.collection.mutable.ArrayBuffer

class GameConfigurationTest extends FunSuite{
  var Controller: Controller = _
  var GameConfiguration: GameConfiguration = _

  override def beforeEach(context: BeforeEach): Unit = {
    Controller = new Controller()
    GameConfiguration = new GameConfiguration(Controller)
  }

  test("The GameConfiguration State can transition as intended") {

    intercept[InvalidTransitionException] {
      GameConfiguration.toEOR()
    }

    intercept[InvalidTransitionException] {
      GameConfiguration.toPassed()
    }
    intercept[InvalidTransitionException] {
      GameConfiguration.toCardSelection()
    }
    Controller.newGame()
    Controller.startGame()

    GameConfiguration.toIdle()
    assert(Controller.getState().isInstanceOf[Idle])
    Controller.setState(GameConfiguration)

    GameConfiguration.toMainMenu()
    assert(Controller.getState().isInstanceOf[MainMenu])
  }
  test("The game configuration screen can create a player and assign them a board") {
    GameConfiguration.setPlayerName("Chile")

    val Player = GameConfiguration.gameStartSettings(0)

    assertEquals("Chile", Player.getName())
    assertNotEquals(ArrayBuffer(), Player.getDeck())
    assertEquals(25, Player.getInitDeck().length)
    assert(Player.getBoard().isInstanceOf[Board] )
  }
  test("The game configuration screen can create randomized enemies for the player") {
    GameConfiguration.setNumberOfRandomEnemies(2)

    val settings = GameConfiguration.gameStartSettings
    val user = settings(0)
    val List = settings(1)

    List -= user

    assertEquals(2, List.length)
    assertNotEquals(List(0), List(1))
  }
  test("The user can choose his enemies") {
    GameConfiguration.setEnemy("Japan")

    val settings = GameConfiguration.gameStartSettings
    val user = settings(0)
    val List = settings(1)

    List -= user

    assertEquals(List(0).getName(), "Japan")
    assertNotEquals(ArrayBuffer(), List(0).getInitDeck())
    assertEquals(25, List(0).getInitDeck().length)
  }

}
