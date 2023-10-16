package cl.uchile.dcc
package gwent.notifications

import cl.uchile.dcc.gwent.controller.*
import cl.uchile.dcc.gwent.player.*
import gwent.cards.*
import gwent.board.*
import scala.collection.mutable.ArrayBuffer
import munit.FunSuite

class PlayerNotifiesControllerTest extends FunSuite{
  var Controller:IController = _
  override def beforeEach(context: BeforeEach): Unit = {
    Controller = new Controller()
    Controller.newGame()
    Controller.setPlayerName("United States")
    Controller.setEnemyName("Japan")
    Controller.startGame()
  }
  test("If a player is defeated, the controller will update their status accordingly"){
    Controller.destroy("Japan")

    val activePlayers = Controller.getActivePlayerNames
    val defeatedPlayers = Controller.getDefeatedPlayerNames
    val playerMap = Controller.playerMap()
    val JapanGems = playerMap("Japan")

    assertEquals(0, JapanGems)
    assert(defeatedPlayers.contains("Japan"))
    assert(!activePlayers.contains("Japan"))
  }
  test("If a player passes, the  controller will update their status accordingly"){
    Controller.User().get.pass()

    assert(Controller.getPassedPlayerNames.contains("United States"))
    assert(!Controller.getActivePlayerNames.contains("United States"))
  }
}
