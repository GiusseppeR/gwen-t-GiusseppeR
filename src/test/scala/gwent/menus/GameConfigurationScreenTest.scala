package cl.uchile.dcc
package gwent.menus

import munit.FunSuite

import scala.collection.mutable.ArrayBuffer

class GameConfigurationScreenTest extends FunSuite{
  var Screen:GameConfigurationScreen = _

  override def beforeEach(context: BeforeEach): Unit = {
    Screen = new GameConfigurationScreen()
  }
  test("The game configuration screen can create a player"){
    Screen.setPlayerName("Chile")
    Screen.startGame()

    val List = Screen.getPlayerList()
    assertEquals("Chile", List(0).getName())
    assertNotEquals(ArrayBuffer(), List(0).getDeck())
    assertEquals(25, List(0).getInitDeck().length)
  }
  test("The game configuration screen can create randomized enemies for the player"){
    Screen.setNumberOfEnemies(2)
    Screen.createEnemies()

    val List = Screen.getPlayerList()

    assertEquals(2, List.length)
    assertNotEquals(List(0).getName(), List(1).getName() )
  }
  test("The user can choose his enemies"){
    Screen.setEnemy("Japan")
    Screen.createEnemies()

    val List = Screen.getPlayerList()

    assertEquals(List(0).getName(), "Japan")
    assertNotEquals(ArrayBuffer(), List(0).getInitDeck())
    assertEquals(25, List(0).getInitDeck().length)
  }

}
