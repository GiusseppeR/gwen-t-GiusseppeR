package cl.uchile.dcc
package gwent.cards

import gwent.board.*
import gwent.cards.*
import gwent.player.*

import java.util.Objects

/** Represents a Close Combat Unit Card
 *
 * Extends AbstractUnitCard
 */
class CloseCombat(name:String,SP:Int) extends AbstractUnitCard(name,SP){

  def goToZone(B: BoardSide): Unit = {
    B.addToCCzone(this)
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
