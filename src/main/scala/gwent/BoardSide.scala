package cl.uchile.dcc
package gwent

import scala.collection.mutable.ArrayBuffer

class BoardSide(name: String) extends AbstractBoard{
  private var CCzone:ArrayBuffer[CloseCombat] = _
  private var RangeZone:ArrayBuffer[Range] = _
  private var SiegeZone:ArrayBuffer[Siege] = _
  private var CardsOnBoard:ArrayBuffer[ICard] = _

  def getPoints():Int = -1
  def getName():String = " "

  def getCCzone():ArrayBuffer[ICard] = ArrayBuffer(new CloseCombat("",0))
  def getRangeZone():ArrayBuffer[ICard] = ArrayBuffer(new Range("",0))

  def getSiegeZone():ArrayBuffer[ICard] = ArrayBuffer(new Siege("",0))


}
