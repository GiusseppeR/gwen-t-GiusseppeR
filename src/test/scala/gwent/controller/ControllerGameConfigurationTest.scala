package cl.uchile.dcc
package gwent.controller

import gwent.controller.*
import gwent.cards.*

import cl.uchile.dcc.gwent.states.*
import munit.FunSuite

class ControllerGameConfigurationTest extends FunSuite{
  var Controller: Controller = _

  override def beforeEach(context: BeforeEach): Unit = {
    Controller = new Controller()
  }
  test("The controller can go from the main menu to the game configuration screen and back"){
    assert(Controller.getState().isInstanceOf[MainMenu])
    Controller.newGame()
    assert(Controller.getState().isInstanceOf[GameConfiguration])
    Controller.goToMenu()
    assert(Controller.getState().isInstanceOf[MainMenu])
  }
  test("The controller can go from the game configuration screen to a new game"){
    Controller.newGame()
    Controller.startGame()
    assert(Controller.getState().isInstanceOf[Idle])
  }
  test("The controller can configure the player's name in the GCS and create it (with a random deck)"){
    Controller.newGame()
    Controller.setPlayerName("Japan")
    Controller.startGame()

    val UserName = Controller.getUserName()

    assertEquals("Japan", UserName)
    assertEquals(10,Controller.User().get.currentHand().length)
    assertEquals(2, Controller.User().get.remainingGems())
  }
  test("The controller can configure enemies and create them (with a random deck)"){
    Controller.newGame()
    Controller.numberOfRandomEnemies(2)
    Controller.setEnemyName("Japan")
    Controller.setEnemyName("Germany")
    Controller.startGame()

    val EnemyInfo = Controller.playerMap() /*a map with the gems of each enemy*/

    assert(EnemyInfo.contains("Japan"))
    assert(EnemyInfo.contains("Germany"))
    assertEquals(5,EnemyInfo.size)
    assertEquals(2, EnemyInfo("Japan"))
    assertEquals(2,EnemyInfo("Germany"))
  }
  test("If all possible enemies where chosen and the player didn't choose a name, the controller doesn't start the game"){
    Controller.newGame()
    Controller.setEnemyName("Germany")
    Controller.setEnemyName("Japan")
    Controller.setEnemyName("Soviet Union")
    Controller.setEnemyName("United States of America")
    Controller.setEnemyName("Great Britain")

    Controller.startGame()

    assert(Controller.getState().isInstanceOf[GameConfiguration])
  }
}
