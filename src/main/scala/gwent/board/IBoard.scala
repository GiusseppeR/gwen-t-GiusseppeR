package cl.uchile.dcc
package gwent.board

import gwent.cards.*
import gwent.player.*

import scala.collection.mutable.ArrayBuffer

trait IBoard {
  def addPlayer(P:Player, sideName: String): Unit
  def getPlayerList():ArrayBuffer[Player]
  def getWinner(): Player
  def getCurrentWeatherCard(): WeatherCard
  def setWeatherCard(C:WeatherCard):Unit

}
