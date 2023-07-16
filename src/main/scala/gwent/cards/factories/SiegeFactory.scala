package cl.uchile.dcc
package gwent.cards.factories

import gwent.cards.*
import gwent.effects.*

class SiegeFactory extends AbstractCardFactory[Siege] {
  override def setSP(n: Int): Unit = {
    SP = Some(n)
  }

  override def createCard(): Siege = new Siege(Name.get, SP.get, Effect.get)
}
