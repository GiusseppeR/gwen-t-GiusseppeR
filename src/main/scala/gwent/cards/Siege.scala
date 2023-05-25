package cl.uchile.dcc
package gwent.cards

import gwent.board.*
import gwent.cards.*
import gwent.player.*

import java.util.Objects

/** Represents a Siege Unit Card
 *
 * Extends AbstractUnitCard
 */
class Siege(name:String,SP:Int) extends AbstractUnitCard(name,SP){

  def goToZone(B: BoardSide): Unit = {
    B.addToSiegeZone(this)
  }

  override def equals(obj: Any): Boolean = {
    if (obj.isInstanceOf[Siege]) {
      val other = obj.asInstanceOf[Siege]
      (this eq other) ||
        other.getName() == name &&
          other.getSP() == SP
    }else {
      false
    }
  }

  override def hashCode(): Int = {
    Objects.hash(classOf[AbstractUnitCard], name, SP)
  }

}
