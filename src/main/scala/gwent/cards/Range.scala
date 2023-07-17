package cl.uchile.dcc
package gwent.cards

import gwent.board.*
import gwent.cards.*
import gwent.cards.ref.*
import gwent.player.*
import gwent.effects.*

import cl.uchile.dcc.gwent.cards.ref.RangeRef

import java.util.Objects

/** Represents a Range Unit Card
 *
 * Extends UnitCard
 */
class Range(name:String,SP:Int) extends AbstractUnitCard(name,SP) {
  /** Secondary constructor.
   * It allows the possibility to create cards with effects.
   *
   * @param name   Name of the card.
   * @param SP     Strength Points of the card.
   * @param Effect Effect of the card.
   */
  def this(name: String, SP: Int, Effect: IEffect) = {
    this(name, SP)
    effect = Effect
    effect.setCard(this)
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
  }

  /** Calls its reference object.
   *
   * @return the RangeRef singleton.
   */
  override def callRef(): Ref = RangeRef

  override def equals(obj: Any): Boolean = {
    if (obj.isInstanceOf[Range]) {
      val other = obj.asInstanceOf[Range]
      (this eq other) ||
        other.getName() == name &&
          other.getSP() == SP &&
          other.getEffect() == effect
    } else {
      false
    }
  }

  override def hashCode(): Int = {
    Objects.hash(classOf[AbstractUnitCard], name, SP)
  }

}
