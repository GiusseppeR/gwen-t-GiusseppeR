package cl.uchile.dcc
package gwent.cards.factories

import gwent.cards.WeatherCard
import gwent.effects.*

class WeatherFactory extends AbstractCardFactory[WeatherCard] {

  override def createCard(): WeatherCard = new WeatherCard(Name.get,Effect.get)

}
