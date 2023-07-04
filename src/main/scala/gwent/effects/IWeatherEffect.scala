package cl.uchile.dcc
package gwent.effects

import gwent.cards.*
import scala.collection.mutable.ArrayBuffer
import gwent.board.*

trait IWeatherEffect extends IEffect{
  def applyToBoard(B: IBoard): Unit
}
