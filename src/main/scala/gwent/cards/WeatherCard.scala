package cl.uchile.dcc
package gwent.cards

import gwent.player.*

import java.util.Objects

/** Represents a Weather Card
 *
 * Extends AbstractCard
 */
class WeatherCard(name:String) extends AbstractCard(name){
  override def sendCommand(P: Player): Unit = {
    P.getBoard().setWeatherCard(this)
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
