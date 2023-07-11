package cl.uchile.dcc
package gwent.cards.pool

import munit.FunSuite
import gwent.cards.*
import gwent.cards.pool.*

import gwent.effects.*

class DefaultCardPoolTest extends FunSuite{
  var Pool:DefaultCardPool = _
  var SpecialUnit:ICard = _

  var MoraleBoost:IUnitEffect    =_
  var TightBond :IUnitEffect  =_

  var Infantry  :ICard     =_
  var Grenadiers :ICard    =_
  var Mobilized :ICard     =_
  var Panzer:ICard         =_

  var CAS:ICard            =_
  var Tactical:ICard       =_
  var Artillery:ICard      =_

  var Strategic:ICard      =_
  var Rocket:ICard         =_
  var Aircraft:ICard       =_

  var Winter:ICard         =_
  var Autumn:ICard         =_
  var Summer:ICard         =_
  var Spring:ICard         =_

  override def beforeEach(context: BeforeEach): Unit = {
    Pool = new DefaultCardPool()
    SpecialUnit = new CloseCombat("British Grenadier", 25, new MoraleBoostEffect())

    MoraleBoost = new MoraleBoostEffect()
    TightBond = new TightBondEffect()

    Infantry = new CloseCombat("Infantry Division", 10)
    Grenadiers = new CloseCombat("Grenadier Division", 20)
    Mobilized = new CloseCombat("Mobilized Infantry", 5, TightBond)
    Panzer = new CloseCombat("Armoured Division", 15, MoraleBoost)


     CAS = new Range("Close Air Support Wing", 20)
     Tactical = new Range("Tactical Bomber Wing", 15)
     Artillery = new Range("Artillery Division", 10, TightBond)

     Strategic = new Siege("Strategic Bomber Wing", 15)
     Rocket = new Siege("Rocket", 30)
     Aircraft = new Siege("Aircraft", 15, MoraleBoost)

     Winter = new WeatherCard("Winter")
     Autumn = new WeatherCard("Autumn")
     Summer = new WeatherCard("Summer")
     Spring = new WeatherCard("Spring")
  }
  test("The DefaultCardPool produces a determined list of cards"){
    val List = Pool.createCardPool()

    assertEquals(4, List.count( C => C.equals(Infantry) ) )
    assertEquals(2, List.count( C => C.equals(Grenadiers) ) )
    assertEquals(3, List.count( C => C.equals(Mobilized) ) )
    assertEquals(2, List.count( C => C.equals(Panzer) ) )

    assertEquals(3, List.count(C => C.equals(CAS)) )
    assertEquals(3, List.count(C => C.equals(Artillery)))
    assertEquals(3, List.count(C => C.equals(Tactical)))

    assertEquals(3, List.count(C => C.equals(Strategic)))
    assertEquals(3, List.count(C => C.equals(Rocket)))
    assertEquals(3, List.count(C => C.equals(Aircraft)))

    assertEquals(2, List.count(C => C.equals(Winter)))
    assertEquals(2, List.count(C => C.equals(Autumn)))
    assertEquals(2, List.count(C => C.equals(Summer)))
    assertEquals(1, List.count(C => C.equals(Spring)))

  }
  test("Cards can be added to the pool"){
    Pool.addToPool(SpecialUnit,2)
    val List = Pool.createCardPool()

    assertEquals(2,List.count(C => C.equals(SpecialUnit)))
  }
  test("Cards can be removed from the pool") {
    Pool.removeFromPool(Mobilized)
    val List = Pool.createCardPool()

    assertEquals(0, List.count(C => C.equals(Mobilized)))

    Pool.removeFromPool(Rocket,1)
    val newList = Pool.createCardPool()

    assertEquals(2, newList.count(C => C.equals(Rocket)))
  }
}
