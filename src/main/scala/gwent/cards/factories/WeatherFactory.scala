package cl.uchile.dcc
package gwent.cards.factories

import gwent.cards.WeatherCard
import gwent.effects.*

/**Represents a weather card factory.
 *
 * Used by CardPool to create weather cards.
 *
 */
class WeatherFactory extends AbstractCardFactory[WeatherCard] {
  /** Override of the method implemented in AbstractCardFactory.
   *
   * Weather cards don't have Strength Points.
   *
   * @param n Number of strength points of the card to be produced.
   */
  override def setSP(n: Int): Unit = {}

  /** Creates a weather card with the parameters set.
   *
   * @return A Weather Card made according to the name and effect variables.
   */
  override def createCard(): WeatherCard = new WeatherCard(Name.get,Effect.get)

}
