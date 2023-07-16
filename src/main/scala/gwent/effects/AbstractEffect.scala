package cl.uchile.dcc
package gwent.effects

import gwent.cards.*

abstract class AbstractEffect extends IEffect {
  protected var selfApply = false
  protected var card:Option[IUnitCard] = None
  protected var ref:Option[IUnitCard] = None

  override def setCard(C: IUnitCard): Unit = {
    card = Some(C)
    if ref.isEmpty then ref = Some(C.callRef())
  }

  override def getReference: Option[IUnitCard] = ref
  override def apply(target: IUnitCard): Unit

}
