package cl.uchile.dcc
package gwent.board

import gwent.cards.*
import gwent.player.*
import gwent.effects.*
import scala.collection.mutable.ArrayBuffer

/** Defines a Board.
 *
 * Methods must be specified in class.
 * Used by Board class.
 */
trait IBoard {
  def addPlayer(P:Player, sideName: String): Unit
  def getPlayerList():ArrayBuffer[Player]
  def getWinner(): Player
  def getCurrentWeatherCard(): WeatherCard
  def setWeatherCard(C:WeatherCard):Unit
  def notifyCards(effect:IEffect):Unit

}
