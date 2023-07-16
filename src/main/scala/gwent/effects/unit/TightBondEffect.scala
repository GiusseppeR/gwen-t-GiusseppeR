package cl.uchile.dcc
package gwent.effects.unit

import gwent.cards.*
import gwent.cards.ref.*
import gwent.effects.ModifySpEffect
import java.util.Objects
import scala.collection.mutable.ArrayBuffer

class TightBondEffect extends ModifySpEffect((x:Int) => 2*x,true){
  def this(reference:IUnitCard) = {
    this()
    ref = Some(reference)
  }
  override def apply(target: IUnitCard): Unit = {
    val cardName = card.get.getName()
    val targetName = target.getName()
    val condition = (cardName == targetName)

    if (selfApply){
      card.get.setSP(function)
      selfApply = false
    }

    if (ref.get.typeCheck(target) && condition){
      target.setSP(function)
    }
  }

  override def equals(obj: Any): Boolean = {
    obj match {
      case _:TightBondEffect => true
      case _ => false
    }
  }

  override def hashCode(): Int = {
    Objects.hash(classOf[TightBondEffect])
  }

}
