package cl.uchile.dcc
package gwent.cards.factories

import gwent.cards.*
import gwent.effects.*

/**Represents a siege card factory.
 *
 * Used by CardPool to create siege cards.
 *
 */
class SiegeFactory extends AbstractCardFactory[Siege] {
  /** Creates a siege card with the parameters set.
   *
   * @return A Siege card made according to the name, SP and effect variables.
   */
  override def createCard(): Siege = new Siege(Name.get, SP.get, Effect.get)
}
