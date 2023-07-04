package cl.uchile.dcc
package gwent.effects
import gwent.cards.{ICard, IUnitCard}

import cl.uchile.dcc.gwent.board.*

import scala.collection.mutable.ArrayBuffer

class NullEffect extends IUnitEffect with IWeatherEffect{
  override def applyToBoard(B: IBoard): Unit = {

  }
  override def apply[T <: IUnitCard] (card: ICard, target: ArrayBuffer[T]): Unit = {

  }

  override def copy(): IEffect = new NullEffect()
}
