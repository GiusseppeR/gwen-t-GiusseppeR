package cl.uchile.dcc
package gwent.cards.pool

import gwent.cards.*
import gwent.cards.factories.*
import gwent.effects.*

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map

class DefaultCardPool extends IPoolOfCards {
  private val MoraleBoost = new MoraleBoostEffect()
  private val TightBond = new TightBondEffect()

  private val Infantry= new CloseCombat("Infantry Division", 10)
  private val Grenadiers = new CloseCombat("Grenadier Division", 20)
  private val Mobilized = new CloseCombat("Mobilized Infantry", 5, TightBond)
  private val Panzer = new CloseCombat("Armoured Division", 15, MoraleBoost)


  private val CAS  = new Range("Close Air Support Wing", 20)
  private val Tactical = new Range("Tactical Bomber Wing", 15)
  private val Artillery = new Range("Artillery Division", 10, TightBond)

  private val Strategic = new Siege("Strategic Bomber Wing", 15)
  private val Rocket = new Siege("Rocket", 30, TightBond)
  private val Aircraft = new Siege("Aircraft", 15)

  private val Winter = new WeatherCard("Winter")
  private val Autumn = new WeatherCard("Autumn")
  private val Summer = new WeatherCard("Summer")
  private val Spring = new WeatherCard("Spring")

  private val CardPool: scala.collection.mutable.Map[ICard, Int] = Map(
    Infantry -> 4,
    Grenadiers -> 2,
    Mobilized -> 3,
    Panzer -> 2,
    CAS -> 3,
    Tactical -> 3,
    Artillery -> 3,
    Strategic -> 3,
    Rocket -> 3,
    Aircraft -> 3,
    Winter -> 2,
    Autumn -> 2,
    Summer -> 2,
    Spring -> 1
  )

  private def printInto(L:ListBuffer[ICard], C:ICard, n:Int):Unit = {
    var i:Int = 0
    while(i < n){
      L.prepend(C)
      i += 1
    }
  }

  override def createCardPool():List[ICard] = {
    val Buf:ListBuffer[ICard] = ListBuffer()
    CardPool.foreach((C:ICard, n:Int) => printInto(Buf,C,n))

    val output = Buf.toList
    output
  }

  override def addToPool(C: ICard,n:Int): Unit = {
    CardPool(C) = n
  }

  override def removeFromPool(C: ICard, n: Int = 0): Unit ={
    n match{
      case 0 => CardPool.remove(C)
      case _ => CardPool(C) = CardPool(C) - n
    }
  }

}
