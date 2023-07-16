package cl.uchile.dcc
package gwent.cards.pool

import cl.uchile.dcc.gwent.cards.factories.*
import gwent.cards.*

import cl.uchile.dcc.gwent.cards.ref.{CloseCombatRef, RangeRef, SiegeRef}
import cl.uchile.dcc.gwent.effects.weather.*
import gwent.effects.*
import gwent.effects.unit.*
import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

class CardPool {
  private val TightBond:IEffect = new TightBondEffect()
  private val MoraleBoost:IEffect = new MoraleBoostEffect()
  private val MoraleBoostCC:IEffect = new MoraleBoostEffect(CloseCombatRef)

  private val bitingFrost:IEffect = new WeatherEffect(CloseCombatRef)
  private val impenetrableFog:IEffect = new WeatherEffect(RangeRef)
  private val torrentialRain:IEffect = new WeatherEffect(SiegeRef)
  private val clearWeather:IEffect = new ClearWeatherEffect()

  private val noEffect:IEffect = new NullEffect()

  private val CCFactory = new CloseCombatFactory()
  private val rangeFactory = new RangeFactory()
  private val SiegeFactory = new SiegeFactory()

  private val WeatherFactory = new WeatherFactory()

  private val Infantry: (String,Int,IEffect) = ("Infantry Division", 10, noEffect)
  private val Grenadiers: (String,Int,IEffect) = ("Grenadier Division", 20, noEffect)
  private val Mobilized:(String,Int,IEffect) = ("Mobilized Infantry", 5, TightBond)
  private val Panzer:(String,Int,IEffect) = ("Armoured Division", 15, MoraleBoost)

  private val CAS: (String,Int,IEffect) = ("Close Air Support Wing", 20,MoraleBoostCC)
  private val Tactical:(String,Int,IEffect) = ("Tactical Bomber Wing", 15,noEffect)
  private val Artillery:(String,Int,IEffect) = ("Artillery Division", 10, TightBond)

  private val Strategic:(String,Int,IEffect) = ("Strategic Bomber Wing", 15,noEffect)
  private val Rocket:(String,Int,IEffect) = ("Rocket", 30, TightBond)
  private val Aircraft:(String,Int,IEffect) = ("Aircraft", 15,noEffect)

  private val Winter:(String,Int,IEffect) = ("Winter",0,bitingFrost)
  private val Autumn:(String,Int,IEffect) = ("Autumn",0,impenetrableFog)
  private val Summer:(String,Int,IEffect) = ("Summer",0,torrentialRain)
  private val Spring:(String,Int,IEffect) = ("Spring",0,clearWeather)

  private var Pool: mutable.Map[(String,Int,IEffect), Int] = mutable.Map(
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
  private var productionAssignation:mutable.Map[(String,Int,IEffect), CardFactory[ICard]] = mutable.Map(
    Infantry -> CCFactory,
    Grenadiers -> CCFactory,
    Mobilized -> CCFactory,
    Panzer -> CCFactory,
    CAS -> rangeFactory,
    Tactical -> rangeFactory,
    Artillery -> rangeFactory,
    Strategic -> SiegeFactory,
    Rocket -> SiegeFactory,
    Aircraft -> SiegeFactory,
    Winter -> WeatherFactory,
    Autumn -> WeatherFactory,
    Summer -> WeatherFactory,
    Spring -> WeatherFactory
  )

  def addToDeck(list:ArrayBuffer[ICard], info: (String,Int,IEffect) ): Boolean = {
    val check = productionAssignation.contains(info)

    if (check) {
      val factory = productionAssignation(info)
      factory.setName(info(0))
      factory.setEffect(info(2))
      factory.setSP(info(1))
      Pool(info) -= 1
      if Pool(info) == 0 then productionAssignation.remove(info)
      val output = factory.createCard()
      list.append(output)
      true
    }else{
      false
    }
  }

  def getCardsInfo: List[(String,Int,IEffect)] = {
    Pool.keys.toList
  }

  def getCardMap: Map[(String,Int,IEffect),Int] = Pool.toMap

  def copy:CardPool = new CardPool()

  protected def addCard[T <:CardFactory[ICard] ](info: (String, Int, IEffect), n: Int, factory:T): Unit = {
    val check = Pool.contains(info)
    if (check){
      Pool(info) += n
    }else{
      Pool += (info -> n)
      productionAssignation += (info -> factory)
    }
  }

  def addWeatherCard(info: (String, Int, IEffect), n: Int):Unit = {
    addCard[WeatherFactory](info,n,WeatherFactory)
  }

  def addCloseCombatCard(info: (String, Int, IEffect), n: Int): Unit = {
    addCard[CloseCombatFactory](info, n, CCFactory)
  }

  def addRangeCard(info: (String, Int, IEffect), n: Int): Unit = {
    addCard[RangeFactory](info, n, rangeFactory)
  }

  def addSiegeCard(info: (String, Int, IEffect), n: Int): Unit = {
    addCard[SiegeFactory](info, n, SiegeFactory)
  }

  def removeFromPool(info: (String, Int, IEffect), n: Int = 0): Unit = {
    if (n == 0){
      Pool.remove(info)
      productionAssignation.remove(info)
    }else if ( n > 0) {
      Pool(info) -= n
      if Pool(info) <= 0 then {
        Pool.remove(info)
        productionAssignation.remove(info)
      }
    }
  }
}
