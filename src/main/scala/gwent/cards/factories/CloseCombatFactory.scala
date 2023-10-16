package cl.uchile.dcc
package gwent.cards.factories

import gwent.cards.*
import cl.uchile.dcc.gwent.effects.IEffect
/**Represents a range card factory.
 *
 * Used by CardPool to create range cards.
 */
class CloseCombatFactory extends AbstractCardFactory[CloseCombat]{
  /** Creates a close combat card with the parameters set.
   *
   * @return A close combat card made according to the name, SP and effect variables.
   */
  override def createCard(): CloseCombat = new CloseCombat(Name.get,SP.get,Effect.get)

}
