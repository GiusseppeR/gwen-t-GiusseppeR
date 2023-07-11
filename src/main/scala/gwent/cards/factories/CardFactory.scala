package cl.uchile.dcc
package gwent.cards.factories
import gwent.cards.{ICard, WeatherCard}

class CardFactory extends ICardFactory {
  private var Card: Option[ICard] = None

  override def setCard(C: ICard): Unit = {
    Card = Some(C)
  }

  override def createCard(): ICard = Card.get
}