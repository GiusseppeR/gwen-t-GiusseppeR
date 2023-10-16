package cl.uchile.dcc
package gwent.effects.weather

import gwent.cards.IUnitCard
import gwent.cards.ref.*
import java.util.Objects
import cl.uchile.dcc.gwent.effects.AbstractEffect

/** Represents a weather effect.
 *
 * It takes a reference object, and sets all the cards of the associated row to 1.
 *
 * @param reference
 * @param n
 *
 * @example
 * {{{
 * val bitingFrost = new WeatherEffect(CloseCombat)
 * val impenetrableFog = new WeatherEffect(Range)
 * val torrentialRain = new WeatherEffect(Siege)
 * }}}
 */
class WeatherEffect(private val reference: Ref, n:Int = 1) extends AbstractEffect{
  ref = Some(reference)

  /** Applies the effect to a card target.
   *
   * @param target Card target,
   */
  override def apply(target: IUnitCard): Unit = {
    target.overrideSp(target.getStackedSP())
    if reference.typeCheck(target) then target.overrideSp(n)
  }

  override def equals(obj: Any): Boolean = {
    if (obj.isInstanceOf[WeatherEffect]) {
      val other = obj.asInstanceOf[WeatherEffect]
      (this eq other) ||
        ref == other.getReference
    } else {
      false
    }
  }

  override def hashCode(): Int = {
    Objects.hash(classOf[WeatherEffect], reference)
  }
}
