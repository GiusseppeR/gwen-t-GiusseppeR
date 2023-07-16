package cl.uchile.dcc
package gwent.cards

import gwent.board.*
import gwent.cards.*
import gwent.player.*
import gwent.effects.*

import cl.uchile.dcc.gwent.cards.ref.CloseCombatRef

import java.util.Objects

/** Represents a Close Combat Unit Card
 *
 * Extends AbstractUnitCard
 */
class CloseCombat(name:String,SP:Int) extends AbstractUnitCard(name,SP){
  def this(name: String, SP:Int, Effect: IEffect) = {
    this(name,SP)
    effect = Effect
    effect.setCard(this)
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
  }

  override def typeCheck(C: IUnitCard): Boolean = {
    C match {
      case _: CloseCombat => true
      case _ => false
    }
  }
  override def callRef(): IUnitCard = CloseCombatRef

  override def equals(obj: Any): Boolean = {
    if (obj.isInstanceOf[CloseCombat]) {
      val other =obj.asInstanceOf[CloseCombat]
      (this eq other) ||
        other.getName() == name &&
          other.getSP() == SP &&
          other.getEffect() == effect
    }else{
      false
    }
  }

  override def hashCode(): Int = {
    Objects.hash(classOf[AbstractUnitCard], name, SP)
  }
}
