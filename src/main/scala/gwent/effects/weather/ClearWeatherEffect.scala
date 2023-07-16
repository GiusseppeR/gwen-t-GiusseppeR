package cl.uchile.dcc
package gwent.effects.weather

import gwent.cards.IUnitCard
import java.util.Objects
import cl.uchile.dcc.gwent.effects.*

class ClearWeatherEffect extends AbstractEffect{
  override def apply(target: IUnitCard): Unit = {
    target.overrideSp(target.getStackedSP())
  }

  override def equals(obj: Any): Boolean = {
    obj match{
      case _:ClearWeatherEffect => true
      case _ => false
    }
  }

  override def hashCode(): Int = {
    Objects.hash(classOf[ClearWeatherEffect])
  }
}
