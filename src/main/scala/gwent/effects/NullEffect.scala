package cl.uchile.dcc
package gwent.effects
import gwent.cards.{ICard, IUnitCard}

import scala.collection.mutable.ArrayBuffer

class NullEffect extends IEffect {
  override def apply(card: ICard, target: IUnitCard): Unit = {

  }
}
