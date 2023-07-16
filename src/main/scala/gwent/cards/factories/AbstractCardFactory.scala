package cl.uchile.dcc
package gwent.cards.factories

import gwent.effects.IEffect

import cl.uchile.dcc.gwent.cards.ICard

abstract class AbstractCardFactory[T <:ICard] extends CardFactory[T] {
  protected var Effect:Option[IEffect] = None
  protected var Name:Option[String] = None
  protected var SP:Option[Int] = None

  override def createCard(): T

  override def setEffect(effect: IEffect): Unit = {
    Effect = Some(effect)
  }

  override def setName(name: String): Unit = {
    Name = Some(name)
  }

  override def setSP(n: Int): Unit = {}
}
