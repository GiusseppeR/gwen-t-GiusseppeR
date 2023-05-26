package cl.uchile.dcc
package gwent.board

import gwent.cards.*
import gwent.player.*
import gwent.board.*

import java.util.Objects
import scala.collection.mutable.ArrayBuffer

class BoardSide(name: String) extends IBoardSide {
  private var CCzone:ArrayBuffer[CloseCombat] = ArrayBuffer()
  private var RangeZone:ArrayBuffer[Range] = ArrayBuffer()
  private var SiegeZone:ArrayBuffer[Siege] = ArrayBuffer()
  private var CardsOnBoard:ArrayBuffer[IUnitCard] = ArrayBuffer()

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

  override def placeCard(C: IUnitCard): Unit = {
    C.goToZone(this)
  }

  override def addToCCzone(C: CloseCombat):Unit = {
    CCzone.append(C)
    CardsOnBoard.append(C)
  }
  override def addToRangeZone(C: Range):Unit = {
    RangeZone.append(C)
    CardsOnBoard.append(C)
  }
  override def addToSiegeZone(C: Siege):Unit = {
    SiegeZone.append(C)
    CardsOnBoard.append(C)
  }

  override def equals(obj: Any): Boolean = {
    if (obj.isInstanceOf[BoardSide]) {
      val other = obj.asInstanceOf[BoardSide]
      (this eq other) ||
        other.getName() == name
    } else {
      false
    }
  }

  override def hashCode(): Int = {
    Objects.hash(classOf[BoardSide],name)
  }
}
