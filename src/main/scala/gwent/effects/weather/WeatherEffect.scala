package cl.uchile.dcc
package gwent.effects.weather

import gwent.cards.IUnitCard
import java.util.Objects
import cl.uchile.dcc.gwent.effects.AbstractEffect

class WeatherEffect(private val reference: IUnitCard, n:Int = 1) extends AbstractEffect{
  ref = Some(reference)

  override def apply(target: IUnitCard): Unit = {
    target.overrideSp(target.getStackedSP())
    if target.typeCheck(reference) then target.overrideSp(n)
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
