package cl.uchile.dcc
package gwent.cards.factories

import gwent.cards.*
import cl.uchile.dcc.gwent.effects.IEffect

class CloseCombatFactory extends AbstractCardFactory[CloseCombat]{
  override def setSP(n: Int): Unit = {
    SP = Some(n)
  }

  override def createCard(): CloseCombat = new CloseCombat(Name.get,SP.get,Effect.get)

}
