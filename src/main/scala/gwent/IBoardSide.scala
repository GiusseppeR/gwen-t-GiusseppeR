package cl.uchile.dcc
package gwent

import scala.collection.mutable.ArrayBuffer

trait IBoardSide {

  def getPoints():Int
  def getName():String
  def getCCzone():ArrayBuffer[CloseCombat]
  def getRangeZone():ArrayBuffer[Range]
  def getSiegeZone():ArrayBuffer[Siege]
  def placeCard(C: IUnitCard):Unit

}
