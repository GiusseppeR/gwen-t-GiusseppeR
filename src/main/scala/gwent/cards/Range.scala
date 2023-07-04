package cl.uchile.dcc
package gwent.cards

import gwent.board.*
import gwent.cards.*
import gwent.player.*
import gwent.effects.*
import java.util.Objects

/** Represents a Range Unit Card
 *
 * Extends UnitCard
 */
class Range(name:String,SP:Int) extends AbstractUnitCard(name,SP) {
  protected var effect: IEffect = new NullEffect()

  def this(name: String, SP: Int, Effect: IEffect) = {
    this(name, SP)
    effect = Effect
  }
  /** Tells the BoardSide object that a Range card was played.
   *
   * Finishes the double dispatch process initiated by the playCard method from Player class.
   * Places the card in its proper zone by calling the addToRangeZone method from BoardSide class.
   *
   * @param B Reference to the board side of a player.
   */
  def goToZone(B: BoardSide): Unit = {
    B.addToRangeZone(this)
    B.applyRangeEffect(this)
  }

  override def equals(obj: Any): Boolean = {
    if (obj.isInstanceOf[Range]) {
      val other = obj.asInstanceOf[Range]
      (this eq other) ||
        other.getName() == name &&
          other.getSP() == SP
    } else {
      false
    }
  }

  override def hashCode(): Int = {
    Objects.hash(classOf[AbstractUnitCard], name, SP)
  }

}
