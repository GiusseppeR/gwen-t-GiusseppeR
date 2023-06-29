package cl.uchile.dcc
package gwent.effects
import gwent.cards.{ICard, IUnitCard}

import scala.collection.mutable.ArrayBuffer

class TightBondEffect extends IEffect{
  override def apply(card: ICard, target: IUnitCard): Unit = {
    if(card.getName().equals( target.getName() ) ){
      target.setSP( 2*target.getSP())
    }
  }
}
