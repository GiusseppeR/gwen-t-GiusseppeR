package cl.uchile.dcc
package gwent.states.controller

import gwent.controller.*

import cl.uchile.dcc.gwent.menus.GameConfigurationScreen
import cl.uchile.dcc.gwent.player.*

import scala.collection.mutable.ListBuffer
class GameConfiguration(context:Controller) extends ControllerState(context){
  private val gameConfiguration = new GameConfigurationScreen()
  override def setNumberOfEnemies(n:Int):Unit = {
    gameConfiguration.setNumberOfEnemies(n)
  }
  override def setName(name:String):Unit = {
    gameConfiguration.setPlayerName(name)
  }
  override def setEnemy(name:String):Unit = {
    gameConfiguration.setEnemy(name)
  }
  override def startGame():ListBuffer[Player] = {
    val out:ListBuffer[Player] = ListBuffer()
    gameConfiguration.createEnemies()
    gameConfiguration.startGame()
    out ++= gameConfiguration.getPlayerList()
    out
  }
  override def toMainMenu(): Unit = {
    context.setState(new MainMenu(context))
  }

  override def toIdle(): Unit = {
    context.setState(new Idle(context))
  }

}
