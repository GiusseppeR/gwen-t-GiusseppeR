package cl.uchile.dcc
package gwent.cards

import gwent.board.*
import gwent.cards.*
import gwent.player.*

import cl.uchile.dcc.gwent.effects.IEffect

/** Represents a Unit Card.
 *
 * Implements IUnitCard;
 * In comparison to an AbstractCard, adds a requirement for a number of Strength Points in order to identify a card.
 *
 * @param name The name of the card.
 * @param SP Number of Strength Points.
 * @constructor Creates a card with a name and a number of Strength Points.
 */
abstract class AbstractUnitCard(name:String, private var SP:Int) extends AbstractCard(name) with IUnitCard{
  protected var effect: IEffect

  override def setSP(n:Int):Unit ={
    SP = n
  }
  /** Provides the number of Strength Points associated with a Unit Card.
   *
   * @return The SP variable used in the constructor.
   */
  override def getSP(): Int = {
    val clone = SP
    clone
  }

  /** Tells the Board Object linked to player that a unit card was played.
   *
   * Method for double dispatch. Used when a player plays a card (see playCard method from Player class).
   * If there is a board side assigned to the player, then further type verification is needed.
   * Therefore, it continues the double dispatch process by calling the placeCard method from BoardSide class.
   *
   * If there is no board side, it does nothing.
   *
   * @param P Player that played the card.
   */
  override def sendCommand(P:Player): Unit = {
    try{
      P.getBoardSide().placeCard(this)
    }catch{
      case e:Exception => println("No board side")
    }
  }

  override def goToZone(B: BoardSide): Unit

}