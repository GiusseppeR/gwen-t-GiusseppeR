package cl.uchile.dcc
package gwent.board

import gwent.player.*
import gwent.cards.*
import gwent.effects.*

import scala.collection.mutable.ArrayBuffer

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
  def placeCard(C: IUnitCard):Unit
  def addToCCzone(C: CloseCombat):Unit
  def addToSiegeZone(C: Siege):Unit
  def addToRangeZone(C:Range):Unit

  def applyCCEffect(C:ICard):Unit
  def applySiegeEffect(C: ICard):Unit
  def applyRangeEffect(C:ICard):Unit

}
