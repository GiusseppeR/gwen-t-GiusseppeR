package cl.uchile.dcc
package gwent

import java.util.Objects

/** Defines a card.
 *
 * getName must be specified in class.
 * Used by Card and its subclasses
 */
trait ICard{
  def getName():String
  def sendCommand(P:Player):Unit
}

/** Represents a generic card.
 *
 * Each generic card is defined only by its name.
 *
 * @param name The name of the card.
 *
 * @constructor Creates a card with a name.
 */
abstract class Card(private val name:String) extends ICard {

  /** Provides the name of the card.
   *
   * @return The name used in the constructor.
   */
  override def getName(): String = name

  def sendCommand(P:Player): Unit

}

/** Represents a Unit Card.
 *
 * Extends Card, and adds a requirement for a number of Strength Points in order to identify a card.
 *
 * @param name The name of the card.
 * @param SP Number of Strength Points.
 *
 * @constructor Creates a card with a name and a number of Strength Points.
 */
abstract class UnitCard(name:String, private var SP:Int) extends Card(name){

  /** Provides the number of Strength Points associated with a Unit Card.
   *
   * @return The SP variable used in the constructor.
   */
  def getSP(): Int = SP

  override def sendCommand(P:Player): Unit = {
    P.getBoardSide().placeCard(this)
  }


}

/** Represents a Close Combat Unit Card
 *
 * Extends UnitCard
 */
class CloseCombat(name:String,SP:Int) extends UnitCard(name,SP){

  def goToZone(B: BoardSide): Unit = {
    B.getCCzone() += this
  }

  override def equals(obj: Any): Boolean = {
    if (obj.isInstanceOf[CloseCombat]) {
      val other =obj.asInstanceOf[CloseCombat]
      (this eq other) ||
        other.getName() == name &&
        other.getSP() == SP
    }else{
      false
    }
  }

  override def hashCode(): Int = {
    Objects.hash(classOf[UnitCard], name, SP)
  }
}
/** Represents a Siege Unit Card
 *
 * Extends UnitCard
 */
class Siege(name:String,SP:Int) extends UnitCard(name,SP){
  def goToZone(B: BoardSide): Unit = {
    B.getSiegeZone() += this
  }

  override def equals(obj: Any): Boolean = {
    if (obj.isInstanceOf[Siege]) {
      val other = obj.asInstanceOf[Siege]
      (this eq other) ||
        other.getName() == name &&
          other.getSP() == SP
    }else {
      false
    }
  }

  override def hashCode(): Int = {
    Objects.hash(classOf[UnitCard], name, SP)
  }

}

/** Represents a Range Unit Card
 *
 * Extends UnitCard
 */
class Range(name:String,SP:Int) extends UnitCard(name,SP) {

  def goToZone(B: BoardSide): Unit = {
    B.getRangeZone() += this
  }

  override def equals(obj: Any): Boolean = {
    if (obj.isInstanceOf[Range]) {
      val other = obj.asInstanceOf[Range]
      (this eq other) ||
        other.getName() == name &&
          other.getSP() == SP
    } else {
      false
    }
  }

  override def hashCode(): Int = {
    Objects.hash(classOf[UnitCard], name, SP)
  }

}

/** Represents a Weather Card
 *
 * Extends Card
 */
class WeatherCard(name:String) extends Card(name){
  override def sendCommand(P: Player): Unit = {
    P.getBoard().placeCard(this)
  }

  def goToZone(B: Board): Unit = {
    B.setWeatherCard(this)
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
    Objects.hash(classOf[Card], name)
  }

}
