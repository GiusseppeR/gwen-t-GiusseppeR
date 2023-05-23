package cl.uchile.dcc
package gwent

/** Represents a generic card.
 *
 * Each generic card is defined only by its name.
 *
 * @param name The name of the card.
 *
 * @constructor Creates a card with a name.
 */
abstract class AbstractCard(private val name:String) extends ICard {

  /** Provides the name of the card.
   *
   * @return The name used in the constructor.
   */
  override def getName(): String = name

  def sendCommand(P:Player): Unit

}
