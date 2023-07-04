package cl.uchile.dcc
package gwent.cards

import gwent.board.*
import gwent.cards.*
import gwent.player.*
import gwent.effects.*
import java.util.Objects

/** Represents a Close Combat Unit Card
 *
 * Extends AbstractUnitCard
 */
class CloseCombat(name:String,SP:Int) extends AbstractUnitCard(name,SP){
  protected var effect: IUnitEffect = new NullEffect()

  def this(name: String, SP:Int, Effect: IUnitEffect) = {
    this(name,SP)
    effect = Effect
  }
  /** Tells to a BoardSide object that a Close Combat card was played.
   *
   * Finishes the double dispatch process initiated by the playCard method from Player class.
   * Places the card in its proper zone by calling the addToCCzone method from BoardSide class.
   *
   * @param B Reference to the board side of a player.
   */
  def goToZone(B: BoardSide): Unit = {
    B.addToCCzone(this)
    B.applyCCEffect(this)
  }

  override def equals(obj: Any): Boolean = {
    if (obj.isInstanceOf[CloseCombat]) {
      val other =obj.asInstanceOf[CloseCombat]
      (this eq other) ||
        other.getName() == name &&
          other.getSP() == SP
    }else{
      false
    }
  }

  override def hashCode(): Int = {
    Objects.hash(classOf[AbstractUnitCard], name, SP)
  }
}
