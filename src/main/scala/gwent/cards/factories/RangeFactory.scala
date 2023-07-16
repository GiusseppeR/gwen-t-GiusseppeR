package cl.uchile.dcc
package gwent.cards.factories

import gwent.cards.*
import gwent.effects.*

class RangeFactory extends AbstractCardFactory[Range] {
  override def setSP(n: Int): Unit = {
    SP = Some(n)
  }

  override def createCard(): Range = new Range(Name.get, SP.get, Effect.get)
}
