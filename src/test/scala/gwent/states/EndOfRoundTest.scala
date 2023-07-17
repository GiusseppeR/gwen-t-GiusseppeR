import cl.uchile.dcc.gwent
import cl.uchile.dcc.gwent.controller.Controller
import cl.uchile.dcc.gwent.states.InvalidTransitionException
import cl.uchile.dcc.gwent.states.controller.*
import munit.FunSuite

class EndOfRoundTest extends FunSuite{
  var Controller:Controller = _
  var EndOfRound:EndOfRound = _

  override def beforeEach(context: BeforeEach): Unit = {
    Controller = new Controller()
    EndOfRound = new EndOfRound(Controller)

    Controller.newGame()
    Controller.setPlayerName("Germany")
    Controller.setEnemyName("Soviet Union")
    Controller.setEnemyName("Great Britain")
    Controller.startGame()
  }
  test("The EndOfRound State can transition as intended"){
    intercept[InvalidTransitionException]{
      EndOfRound.toEOR()
    }
    intercept[InvalidTransitionException] {
      EndOfRound.toCardSelection()
    }
    intercept[InvalidTransitionException] {
      EndOfRound.toPassed()
    }
    intercept[InvalidTransitionException] {
      EndOfRound.toGameConfiguration()
    }

    EndOfRound.toIdle()
    assert(Controller.getState().isInstanceOf[Idle])
    Controller.setState(EndOfRound)
    EndOfRound.toMainMenu()
    assert(Controller.getState().isInstanceOf[MainMenu])
  }
  test("The EOR state can finish the round"){
    Controller.Pass()
    EndOfRound.finishRound()

    val PlayerMap = Controller.playerMap()
    assertEquals(1,Controller.showUserGems())
    assert(Controller.getState().isInstanceOf[Idle])
  }
  test("If the player loses, the EOR state makes the controller go to the Main Menu state"){
    Controller.destroy("Germany")
    EndOfRound.transition()
    assert(Controller.getState().isInstanceOf[MainMenu])
  }
  test("If the player wins, the EOR makes the controller go to the Main Menu state"){
    Controller.destroy("Soviet Union")
    Controller.destroy("Great Britain")
    EndOfRound.transition()
    assert(Controller.getState().isInstanceOf[MainMenu])
  }
}
