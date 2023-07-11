package cl.uchile.dcc
package gwent.menus

import gwent.player.*


trait IGameConfiguration {
  def setPlayerName(name:String):Unit
  def setNumberOfEnemies(n:Int):Unit
  def setEnemy(name:String):Unit
  def createEnemies():Unit
  def getPlayerList():List[Player]
  def startGame():Unit
}
