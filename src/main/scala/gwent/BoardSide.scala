package cl.uchile.dcc
package gwent

import scala.collection.mutable.ArrayBuffer

class BoardSide(name: String) extends AbstractBoard{
  private var CCzone:ArrayBuffer[CloseCombat] = ArrayBuffer()
  private var RangeZone:ArrayBuffer[Range] = ArrayBuffer()
  private var SiegeZone:ArrayBuffer[Siege] = ArrayBuffer()
  private var CardsOnBoard:ArrayBuffer[UnitCard] = ArrayBuffer()

  def getPoints():Int = {
    var suma: Int = 0
    for(card <- CardsOnBoard){
      suma += card.getSP()
    }
    suma
  }
  def getName():String = name

  def getCCzone():ArrayBuffer[CloseCombat] = CCzone
  def getRangeZone():ArrayBuffer[Range] = RangeZone

  def getSiegeZone():ArrayBuffer[Siege] = SiegeZone


}
