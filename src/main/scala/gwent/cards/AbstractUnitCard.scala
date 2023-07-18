package cl.uchile.dcc
package gwent.cards

import gwent.board.*
import gwent.cards.*
import gwent.cards.ref.*
import gwent.player.*
import gwent.observer.*
import cl.uchile.dcc.gwent.effects.*

/** Represents a Unit Card.
 *
 * Implements IUnitCard;
 * In comparison to an AbstractCard, adds a requirement for a number of Strength Points in order to identify a card.
 *
 * Part of an observer pattern.
 * The UnitCards observe the board and board side in which they are placed.
 * @param name The name of the card.
 * @param SP Number of Strength Points.
 * @constructor Creates a card with a name and a number of Strength Points.
 */
abstract class AbstractUnitCard(name:String, private var SP:Int) extends AbstractCard(name) with IUnitCard with Observer[IEffect]{
  private var stackedSP = SP
  private var currentSP = SP

  /** Allows to change the currentSP.
   * Used by the effects.
   *
   * @param f function that indicates how to modify the SP of the card.
   */
  override def setSP(f: Int => Int):Unit ={
    stackedSP = f(stackedSP)
    currentSP = stackedSP
  }

  /**Overrides the SP of the card.
   * Used by the effects.
   *
   * @param n New SP.
   */
  override def overrideSp(n: Int): Unit = {
    currentSP = n
  }
  /** Provides the current number of Strength Points associated with a Unit Card.
   *
   * @return The currentSP variable.
   */
  override def getSP(): Int = {
    val clone = currentSP
    clone
  }

  /** Provides the total stacked number of SP, without considering any possible override.
   *
   * @return The stackedSP variable.
   */
  override def getStackedSP(): Int = {
    val clone = stackedSP
    clone
  }

  /** Tells the Board Object linked to player that a unit card was played.
   *
   * Method for double dispatch. Used when a player plays a card (see playCard method from Player class).
   * If there is a board side assigned to the player, then further type verification is needed.
   * Therefore, it continues the double dispatch process by calling the placeCard method from BoardSide class.
   *
   * It also subscribes the cards to the player board.
   *
   * If there is no board side, it does nothing.
   *
   * @param P Player that played the card.
   */
  override def sendCommand(P:Player): Unit = {
    try{
      val weatherEffect = P.getBoard().getCurrentWeatherCard().getEffect()
      P.getBoardSide().placeCard(this,this)
      P.getBoard().addObserver(this)
      P.getBoard().notifyObservers(weatherEffect)

    }catch{
      case e:NoSuchElementException => println("No board side")
    }
  }

  override def goToZone(B: BoardSide): Unit
  override def callRef(): Ref
  override def update(subject: Subject[IEffect], value:IEffect):Unit = {
    value(this)
  }

}