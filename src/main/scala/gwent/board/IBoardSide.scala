package cl.uchile.dcc
package gwent.board

import gwent.player.*
import gwent.cards.*
import gwent.effects.*

import scala.collection.mutable.ArrayBuffer
import gwent.observer.*

/** Defines a board side.
 *
 * Methods must be specified in class.
 * Used by BoardSide class.
 */

trait IBoardSide {

  def getPoints():Int
  def getName():String
  def getCCzone():ArrayBuffer[CloseCombat]
  def getRangeZone():ArrayBuffer[Range]
  def getSiegeZone():ArrayBuffer[Siege]
  def placeCard(C: IUnitCard, O: Observer[IEffect]):Unit
  def addToCCzone(C: CloseCombat):Unit
  def addToSiegeZone(C: Siege):Unit
  def addToRangeZone(C:Range):Unit

}
