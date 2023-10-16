package cl.uchile.dcc
package gwent.effects
import gwent.cards.{AbstractCard, ICard, IUnitCard}

import cl.uchile.dcc.gwent.board.*

import java.util.Objects
import scala.collection.mutable.ArrayBuffer

/** An effect that does nothing.
 */

class NullEffect extends AbstractEffect{
  /**Applies the effect to a target.
   *
   * @param target A target card.
   */
  override def apply(target: IUnitCard): Unit = {}

  override def equals(obj: Any): Boolean = {
    obj match {
      case _:NullEffect => true
      case _ => false
    }
  }

  override def hashCode(): Int = {
    Objects.hash(classOf[NullEffect])
  }
}
