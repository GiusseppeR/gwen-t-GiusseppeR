package cl.uchile.dcc
package gwent.cards

import gwent.player.*

import cl.uchile.dcc.gwent.effects.{IEffect, NullEffect}

import java.util.Objects

/** Represents a Weather Card
 *
 * Extends AbstractCard
 */
class WeatherCard(name:String) extends AbstractCard(name){
  protected var effect: IEffect = new NullEffect()

  def this(name:String, Effect: IEffect) = {
    this(name)
    effect = Effect
  }

  /** Tells the Board Object linked to player that a weather card was played.
   *
   * Method for double dispatch. Used when a player plays a card (see playCard method from Player class).
   * If there is a board, then it puts it in its place by calling the setWeatherCard method from Board class.
   * If there is no board, it does nothing.
   *
   * @param P Player that played the card.
   */
  override def sendCommand(P: Player): Unit = {
    try{
      P.getBoard().setWeatherCard(this)
    } catch{
      case e:Exception => println("No Board")
    }
  }

  override def equals(obj: Any): Boolean = {

    if (obj.isInstanceOf[WeatherCard]) {
      val other = obj.asInstanceOf[WeatherCard]
      (this eq other) ||
        other.getName() == name
    } else {
      false
    }
  }


  override def hashCode(): Int = {
    Objects.hash(classOf[AbstractCard], name)
  }

}
