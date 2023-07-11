package cl.uchile.dcc
package gwent.controller

import gwent.controller.*
import gwent.cards.*
import cl.uchile.dcc.gwent.states.controller.*
import munit.FunSuite

class ControllerGameConfigurationScreenTest extends FunSuite{
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
    val UserHand = Controller.showUserHand()
    val UserGems = Controller.showUserGems()

    assertEquals("Japan", UserName)
    assertEquals(10,UserHand.length)
    assert(UserHand.isInstanceOf[List[ICard]])
    assertEquals(2, UserGems)
  }
  test("The controller can configure enemies and create them (with a random deck)"){
    Controller.newGame()
    Controller.numberOfEnemies(2)
    Controller.setEnemyName("Japan")
    Controller.setEnemyName("Germany")
    Controller.startGame()

    val EnemyInfo = Controller.playerMap() /*a map with the gems of each enemy*/

    assert(EnemyInfo.contains("Japan"))
    assert(EnemyInfo.contains("Germany"))

    assertEquals(2, EnemyInfo("Japan")(1))
    assertEquals(2,EnemyInfo("Germany")(1))
  }
}
