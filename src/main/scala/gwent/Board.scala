package cl.uchile.dcc
package gwent

import scala.collection.mutable.ArrayBuffer
class Board extends AbstractBoard{
  private var weatherZone:ArrayBuffer[WeatherCard] = _
  private var playerList: ArrayBuffer[Player] = _

  def addPlayer(P:Player, sideName: String): Unit = {

  }

  def getPlayerList():ArrayBuffer[Player] = playerList
  def getWinner(): Player = new Player("",ArrayBuffer(new CloseCombat(" ",0)))

  def getCurrentWeatherCard():WeatherCard = new WeatherCard("")

  def setWeatherCard(C:ICard):Unit = {

  }
}
