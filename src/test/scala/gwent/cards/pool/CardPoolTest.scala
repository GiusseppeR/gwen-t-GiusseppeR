package cl.uchile.dcc
package gwent.cards.pool

import munit.FunSuite
import gwent.cards.*
import gwent.cards.pool.*
import gwent.effects.*
import cl.uchile.dcc.gwent.cards.factories.{CloseCombatFactory, RangeFactory, SiegeFactory, WeatherFactory}
import cl.uchile.dcc.gwent.cards.ref.*
import cl.uchile.dcc.gwent.effects.unit.{MoraleBoostEffect, TightBondEffect}
import cl.uchile.dcc.gwent.effects.weather.*

import scala.collection.mutable.ArrayBuffer

class CardPoolTest extends FunSuite{
  var Pool: CardPool = _

  var MoraleBoost:IEffect    =_
  var MoraleBoostCC: IEffect =_
  var TightBond :IEffect  =_
  var NoEffect:IEffect =_

   var bitingFrost:IEffect = _
   var impenetrableFog:IEffect = _
   var torrentialRain:IEffect = _
   var clearWeather:IEffect = _

  var Infantry  :(String,Int,IEffect)     =_
  var Grenadiers :(String,Int,IEffect)    =_
  var Mobilized :(String,Int,IEffect)     =_
  var Panzer:(String,Int,IEffect)         =_

  var CAS:(String,Int,IEffect)            =_
  var Tactical:(String,Int,IEffect)       =_
  var Artillery:(String,Int,IEffect)      =_

  var Strategic:(String,Int,IEffect)      =_
  var Rocket:(String,Int,IEffect)         =_
  var Aircraft:(String,Int,IEffect)       =_

  var Winter:(String,Int,IEffect)         =_
  var Autumn:(String,Int,IEffect)         =_
  var Summer:(String,Int,IEffect)         =_
  var Spring:(String,Int,IEffect)         =_

  override def beforeEach(context: BeforeEach): Unit = {
    Pool = new CardPool()

    MoraleBoost = new MoraleBoostEffect()
    MoraleBoostCC = new MoraleBoostEffect(CloseCombatRef)
    TightBond = new TightBondEffect()
    NoEffect = new NullEffect()

    bitingFrost = new WeatherEffect(CloseCombatRef)
    impenetrableFog = new WeatherEffect(RangeRef)
    torrentialRain = new WeatherEffect(SiegeRef)
    clearWeather = new ClearWeatherEffect()

    Infantry = ("Infantry Division", 10, NoEffect)
    Grenadiers = ("Grenadier Division", 20,NoEffect)
    Mobilized = ("Mobilized Infantry", 5, TightBond)
    Panzer = ("Armoured Division", 15, MoraleBoost)


     CAS = ("Close Air Support Wing", 20,MoraleBoostCC)
     Tactical = ("Tactical Bomber Wing", 15,NoEffect)
     Artillery = ("Artillery Division", 10, TightBond)

     Strategic = ("Strategic Bomber Wing", 15,NoEffect)
     Rocket = ("Rocket", 30, TightBond)
     Aircraft = ("Aircraft", 15,NoEffect)

     Winter = ("Winter", 0, bitingFrost)
     Autumn = ("Autumn", 0, impenetrableFog)
     Summer = ("Summer", 0, torrentialRain)
     Spring = ("Spring", 0, clearWeather)
  }
  test("The CardPool produces a determined list of cards"){
    val cardPoolList = Pool.getCardsInfo

    assert(cardPoolList.contains(Infantry))
    assert(cardPoolList.contains(Grenadiers))
    assert(cardPoolList.contains(Mobilized))
    assert(cardPoolList.contains(Panzer))

    assert(cardPoolList.contains(CAS))
    assert(cardPoolList.contains(Tactical))
    assert(cardPoolList.contains(Artillery))

    assert(cardPoolList.contains(Rocket))
    assert(cardPoolList.contains(Strategic))
    assert(cardPoolList.contains(Aircraft))

    assert(cardPoolList.contains(Spring))
    assert(cardPoolList.contains(Summer))
    assert(cardPoolList.contains(Winter))
    assert(cardPoolList.contains(Autumn))

  }
  test("The Pool can add cards to an array, accordingly to the amount established in the standard deck"){
    val output: ArrayBuffer[ICard] = ArrayBuffer()

    val Card1 = new CloseCombat(Infantry(0),Infantry(1))
    val Card2 = new Range(CAS(0),CAS(1),CAS(2))
    val Card3 = new Siege(Rocket(0),Rocket(1),Rocket(2))
    val Card4 = new WeatherCard(Spring(0),Spring(2))

    assert(Pool.addToDeck(output,Infantry))
    assert(Pool.addToDeck(output,CAS))
    assert(Pool.addToDeck(output,Rocket))
    assert(Pool.addToDeck(output,Spring))
    assert(!Pool.addToDeck(output,Spring))

    assertEquals(output, ArrayBuffer[ICard](Card1,Card2,Card3,Card4))
  }
  test("Cards can be added to the pool"){
    val SpecialUnit = ("British Grenadier", 25, MoraleBoost)
    val NuclearWinter = ("Nuclear Winter",0, impenetrableFog)
    val Nuke = ("Nuclear Bomb", 100,NoEffect)
    val SpaceShip = ("Space Ship", 10,NoEffect)

    Pool.addCloseCombatCard(SpecialUnit,2)
    assert(Pool.getCardsInfo.contains(SpecialUnit))
    Pool.addWeatherCard(NuclearWinter,2)
    assert(Pool.getCardsInfo.contains(NuclearWinter))
    Pool.addSiegeCard(Nuke,1)
    assert(Pool.getCardsInfo.contains(Nuke))
    Pool.addRangeCard(SpaceShip,1)
    assert(Pool.getCardsInfo.contains(SpaceShip))

    Pool.addRangeCard(CAS,2)

    val CardMap = Pool.getCardMap

    assertEquals(5,CardMap(CAS))
    assertEquals(2,CardMap(SpecialUnit))
    assertEquals(2,CardMap(NuclearWinter))
    assertEquals(1,CardMap(Nuke))
    assertEquals(1,CardMap(SpaceShip))
  }
}
