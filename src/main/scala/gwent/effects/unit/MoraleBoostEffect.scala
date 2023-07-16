package cl.uchile.dcc
package gwent.effects.unit

import gwent.cards.*
import gwent.effects.ModifySpEffect

import cl.uchile.dcc.gwent.cards.ref.CloseCombatRef

import java.util.Objects
import scala.collection.mutable.ArrayBuffer

class MoraleBoostEffect extends ModifySpEffect((x:Int) => x+1,false){
  def this(reference: IUnitCard) = {
    this()
    ref = Some(reference)
  }
  override def apply(target: IUnitCard): Unit = {
    if ref.get.typeCheck(target) then target.setSP(function)
  }

  override def equals(obj: Any): Boolean = {
    obj match {
      case _: MoraleBoostEffect => true
      case _ => false
    }
  }

  override def hashCode(): Int = {
    Objects.hash(classOf[MoraleBoostEffect])
  }
}

