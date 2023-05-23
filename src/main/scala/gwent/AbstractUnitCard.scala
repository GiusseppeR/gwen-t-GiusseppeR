package cl.uchile.dcc
package gwent

/** Represents a Unit Card.
 *
 * Implements IUnitCard;
 * In comparison to an AbstractCard, adds a requirement for a number of Strength Points in order to identify a card.
 *
 * @param name The name of the card.
 * @param SP Number of Strength Points.
 *
 * @constructor Creates a card with a name and a number of Strength Points.
 */
abstract class AbstractUnitCard(name:String, private var SP:Int) extends AbstractCard(name) with IUnitCard{

  /** Provides the number of Strength Points associated with a Unit Card.
   *
   * @return The SP variable used in the constructor.
   */
  override def getSP(): Int = SP

  override def sendCommand(P:Player): Unit = {
    P.getBoardSide().placeCard(this)
  }

  override def goToZone(B: BoardSide): Unit

}