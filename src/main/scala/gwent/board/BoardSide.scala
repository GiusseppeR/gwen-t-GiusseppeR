package cl.uchile.dcc
package gwent.board

import gwent.cards.*
import gwent.player.*
import gwent.board.*

import cl.uchile.dcc.gwent.effects.IEffect

import java.util.Objects
import scala.collection.mutable.ArrayBuffer

/** Represents a board side.
 *
 * Each board side is identified by its name.
 * A board side has three zones to store unit cards, one for each type (close combat, siege, range).
 *
 * @param name The name of the board side.
 *
 * @constructor Creates an empty board side with a name.
 */
class BoardSide(private val name: String) extends IBoardSide {
  override def notifyCards(C:ICard, effect: IEffect): Unit = {
    for(card <- CardsOnBoard){
      card.update(C,effect)
    }
  }
  /** Zone of close combat cards.
   * Stores close combat cards played.
   */
  private var CCzone:ArrayBuffer[CloseCombat] = ArrayBuffer()

  /**Zone of range cards.
   * Stores range cards played.
   */
  private var RangeZone:ArrayBuffer[Range] = ArrayBuffer()

  /**Zone of siege cards.
   * Stores siege cards played.
   */
  private var SiegeZone:ArrayBuffer[Siege] = ArrayBuffer()

  /**Array that stores all unit cards played.
   */
  private var CardsOnBoard:ArrayBuffer[IUnitCard] = ArrayBuffer()

  /**Provides the name of the board side.
   *
   * @return name used in the constructor.
   */
  def getName():String = name

  /**Gives access to the close combat zone.
   *
   * @return Array of all close combat cards played.
   */
  def getCCzone():ArrayBuffer[CloseCombat] = {
    val clone = CCzone
    clone
  }

  /** Gives access to the range zone.
   *
   * @return Array of all range cards played.
   */
  def getRangeZone():ArrayBuffer[Range] = {
    val clone = RangeZone
    clone
  }

  /** Gives access to the siege zone.
   *
   * @return Array of all siege cards played.
   */
  def getSiegeZone():ArrayBuffer[Siege] = {
    val clone = SiegeZone
    clone
  }

  /** Gives the order to place a card in its relative zone.
   *
   * Method for double dispatch. It might come out as redundant given the sendCommand and goToZone methods in the card classes,
   * but it makes it a bit easier for the developer to test and control the board side.
   *
   * @param C Card to be placed
   *
   * @example
   * {{{
   * var BS = new BoardSide("Ottoman Balkans")
   * BS.placeCard(new CloseCombat("Janissary", 100))
   * }}}
   */
  override def placeCard(C: IUnitCard): Unit = {
    C.goToZone(this)
  }

  /** Adds a given card to the close combat zone.
   *
   * Appends a close combat card to CCzone and CardsOnBoard.
   *
   * @param C Close combat card added.
   */
  override def addToCCzone(C: CloseCombat):Unit = {
    CCzone.append(C)
    CardsOnBoard.append(C)
  }

  /** Adds a given card to the range zone.
   *
   * Appends a range card to RangeZone and CardsOnBoard.
   *
   * @param C Range card added.
   */
  override def addToRangeZone(C: Range):Unit = {
    RangeZone.append(C)
    CardsOnBoard.append(C)
  }

  /** Adds a given card to the siege zone.
   *
   * Appends a siege card to SiegeZone and CardsOnBoard.
   *
   * @param C Siege card added.
   */
  override def addToSiegeZone(C: Siege):Unit = {
    SiegeZone.append(C)
    CardsOnBoard.append(C)
  }

  /** Provides the sum of the strength points of all cards played.
   *
   * @return An integer; sum of the strength points of all elements in CardsOnBoard.
   */
  def getPoints(): Int = {
    var sum: Int = 0
    for (card <- CardsOnBoard) {
      sum += card.getSP()
    }
    sum
  }

  override def equals(obj: Any): Boolean = {
    if (obj.isInstanceOf[BoardSide]) {
      val other = obj.asInstanceOf[BoardSide]
      (this eq other) ||
        other.getName() == name
    } else {
      false
    }
  }

  override def hashCode(): Int = {
    Objects.hash(classOf[BoardSide],name)
  }
}
