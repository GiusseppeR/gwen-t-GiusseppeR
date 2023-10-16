package cl.uchile.dcc
package gwent.factories

import munit.FunSuite
import gwent.cards.factories.*
import gwent.cards.*
import cl.uchile.dcc.gwent.cards.ref.CloseCombatRef
import cl.uchile.dcc.gwent.effects.unit.TightBondEffect
import cl.uchile.dcc.gwent.effects.weather.WeatherEffect

class WeatherFactoryTest extends FunSuite{
  var Factory:WeatherFactory = _

  override def beforeEach(context: BeforeEach): Unit = {
    Factory = new WeatherFactory()
  }
  test("The factory can create a Close Combat Card"){
    Factory.setName("uwu")
    Factory.setEffect(new WeatherEffect(CloseCombatRef))

    assertEquals(new WeatherCard("uwu",new WeatherEffect(CloseCombatRef) ), Factory.createCard())
  }
}