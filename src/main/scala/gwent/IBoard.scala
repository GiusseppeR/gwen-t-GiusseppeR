package cl.uchile.dcc
package gwent

import scala.collection.mutable.ArrayBuffer

trait IBoard {
  def addPlayer(P:Player, sideName: String): Unit
  def getPlayerList():ArrayBuffer[Player]
  def getWinner(): Option[Player]
  def getCurrentWeatherCard(): WeatherCard
  def setWeatherCard(C:WeatherCard):Unit

}
