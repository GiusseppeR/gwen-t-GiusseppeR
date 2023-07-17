package cl.uchile.dcc
package gwent.factories

import munit.FunSuite
import gwent.cards.factories.*
import gwent.cards.*

import cl.uchile.dcc.gwent.effects.unit.TightBondEffect

class CloseCombatFactoryTest extends FunSuite{
  var Factory:CloseCombatFactory = _

  override def beforeEach(context: BeforeEach): Unit = {
    Factory = new CloseCombatFactory()
  }
  test("The factory can create a Close Combat Card"){
    Factory.setName("uwu")
    Factory.setSP(10)
    Factory.setEffect(new TightBondEffect())

    assertEquals(new CloseCombat("uwu",10,new TightBondEffect() ), Factory.createCard())
  }
}
