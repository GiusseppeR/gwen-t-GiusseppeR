package cl.uchile.dcc
package gwent

import java.util.Objects

/** Represents a Range Unit Card
 *
 * Extends UnitCard
 */
class Range(name:String,SP:Int) extends AbstractUnitCard(name,SP) {

  def goToZone(B: BoardSide): Unit = {
    B.addToRangeZone(this)
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
