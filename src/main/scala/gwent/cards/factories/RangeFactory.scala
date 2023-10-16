package cl.uchile.dcc
package gwent.cards.factories

import gwent.cards.*
import gwent.effects.*

/**Represents a range card factory.
 *
 * Used by CardPool to create range cards.
 *
 */
class RangeFactory extends AbstractCardFactory[Range] {
  /** Creates a range card with the parameters set.
   *
   * @return A Siege card made according to the name, SP and effect variables.
   */
  override def createCard(): Range = new Range(Name.get, SP.get, Effect.get)
}
