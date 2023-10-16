package cl.uchile.dcc
package gwent.cards.pool

import cl.uchile.dcc.gwent.cards.factories.*
import gwent.cards.*

import cl.uchile.dcc.gwent.cards.ref.{CloseCombatRef, RangeRef, SiegeRef}
import cl.uchile.dcc.gwent.effects.weather.*
import gwent.effects.*
import gwent.effects.unit.*
import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

/** Represents a standard pool of cards.
 *
 *  Defines the information of all the cards meant to be used in the game as follows:
 *  val CardInfo = (Name, SP, Effect)
 *  For weather cards, SP is set to 0.
 *
 *  Uses the card factories to actually create the cards.
 *
 *  Used by the GameConfiguration state in Controller.
 *  Each player is meant to have its own pool of cards.
 *
 */
class CardPool {

  private val TightBond:IEffect = new TightBondEffect()
  private val MoraleBoost:IEffect = new MoraleBoostEffect()
  private val MoraleBoostCC:IEffect = new MoraleBoostEffect(CloseCombatRef)

  private val bitingFrost:IEffect = new WeatherEffect(CloseCombatRef)
  private val impenetrableFog:IEffect = new WeatherEffect(RangeRef)
  private val torrentialRain:IEffect = new WeatherEffect(SiegeRef)
  private val clearWeather:IEffect = new ClearWeatherEffect()

  private val noEffect:IEffect = new NullEffect()

  private val CCFactory = new CloseCombatFactory()
  private val rangeFactory = new RangeFactory()
  private val SiegeFactory = new SiegeFactory()

  private val WeatherFactory = new WeatherFactory()

  private val Infantry: (String,Int,IEffect) = ("Infantry Division", 10, noEffect)
  private val Grenadiers: (String,Int,IEffect) = ("Grenadier Division", 20, noEffect)
  private val Mobilized:(String,Int,IEffect) = ("Mobilized Infantry", 5, TightBond)
  private val Panzer:(String,Int,IEffect) = ("Armoured Division", 15, MoraleBoost)

  private val CAS: (String,Int,IEffect) = ("Close Air Support Wing", 20,MoraleBoostCC)
  private val Tactical:(String,Int,IEffect) = ("Tactical Bomber Wing", 15,noEffect)
  private val Artillery:(String,Int,IEffect) = ("Artillery Division", 10, TightBond)

  private val Strategic:(String,Int,IEffect) = ("Strategic Bomber Wing", 15,noEffect)
  private val Rocket:(String,Int,IEffect) = ("Rocket", 30, TightBond)
  private val Aircraft:(String,Int,IEffect) = ("Aircraft", 15,noEffect)

  private val Winter:(String,Int,IEffect) = ("Winter",0,bitingFrost)
  private val Autumn:(String,Int,IEffect) = ("Autumn",0,impenetrableFog)
  private val Summer:(String,Int,IEffect) = ("Summer",0,torrentialRain)
  private val Spring:(String,Int,IEffect) = ("Spring",0,clearWeather)

  /** Assigns each card a number, representing their frequency (and amount left) in the standard deck.
   */
  private var Pool: mutable.Map[(String,Int,IEffect), Int] = mutable.Map(
    Infantry -> 4,
    Grenadiers -> 2,
    Mobilized -> 3,
    Panzer -> 2,
    CAS -> 3,
    Tactical -> 3,
    Artillery -> 3,
    Strategic -> 3,
    Rocket -> 3,
    Aircraft -> 3,
    Winter -> 2,
    Autumn -> 2,
    Summer -> 2,
    Spring -> 1
  )

  /** Associates each card with a factory.
   */
  private var productionAssignation:mutable.Map[(String,Int,IEffect), CardFactory[ICard]] = mutable.Map(
    Infantry -> CCFactory,
    Grenadiers -> CCFactory,
    Mobilized -> CCFactory,
    Panzer -> CCFactory,
    CAS -> rangeFactory,
    Tactical -> rangeFactory,
    Artillery -> rangeFactory,
    Strategic -> SiegeFactory,
    Rocket -> SiegeFactory,
    Aircraft -> SiegeFactory,
    Winter -> WeatherFactory,
    Autumn -> WeatherFactory,
    Summer -> WeatherFactory,
    Spring -> WeatherFactory
  )

  /** Adds a card to an array.
   *
   * Takes an arrayBuffer of cards and a 3-tuple with the information of the card to be added,
   * and proceeds to include it in the array.
   *
   * When a card is added to an array, it reduces its number associated in the Pool variable by one.
   * If the card information indicated has a 0 assigned in Pool, it does nothing.
   *
   * @param list ArrayBuffer of cards. Destiny of the card.
   * @param info Information of the card to be added.
   * @return A boolean, indicating if the addition was succesful.
   */
  def addToDeck(list:ArrayBuffer[ICard], info: (String,Int,IEffect) ): Boolean = {
    val check = productionAssignation.contains(info)

    if (check) {
      val factory = productionAssignation(info)
      factory.setName(info(0))
      factory.setEffect(info(2))
      factory.setSP(info(1))
      Pool(info) -= 1
      if Pool(info) == 0 then productionAssignation.remove(info)
      val output = factory.createCard()
      list.append(output)
      true
    }else{
      false
    }
  }

  /** Provides a list with all the cards in the pool.
   *
   * @return A list with the information of all the cards in the pool.
   */
  def getCardsInfo: List[(String,Int,IEffect)] = {
    Pool.keys.toList
  }

  /** Provides a copy of the Pool variable.
   *
   * @return A copy of the Pool variable.
   */
  def getCardMap: Map[(String,Int,IEffect),Int] = Pool.toMap

  /** Provides a new CardPool.
   *
   * @return A new CardPool
   */
  def copy:CardPool = new CardPool()

  /** Generic base for adding new cards to the pool.
   *
   * @param info Information of the new card.
   * @param n Frequency of the card.
   * @param factory Card Factory associated.
   * @tparam T Type of the factory
   */
  protected def addCard[T <:CardFactory[ICard] ](info: (String, Int, IEffect), n: Int, factory:T): Unit = {
    val check = Pool.contains(info)
    if (check){
      Pool(info) += n
    }else{
      Pool += (info -> n)
      productionAssignation += (info -> factory)
    }
  }

  /** Method for adding weather cards.
   *
   * @param info Information of the card.
   * @param n Amount to be added.
   */
  def addWeatherCard(info: (String, Int, IEffect), n: Int):Unit = {
    addCard[WeatherFactory](info,n,WeatherFactory)
  }

  /** Method for adding close combat cards.
   *
   * @param info Information of the card.
   * @param n    Amount to be added.
   */
  def addCloseCombatCard(info: (String, Int, IEffect), n: Int): Unit = {
    addCard[CloseCombatFactory](info, n, CCFactory)
  }

  /** Method for adding range cards.
   *
   * @param info Information of the card.
   * @param n    Amount to be added.
   */
  def addRangeCard(info: (String, Int, IEffect), n: Int): Unit = {
    addCard[RangeFactory](info, n, rangeFactory)
  }

  /** Method for adding siege cards.
   *
   * @param info Information of the card.
   * @param n    Amount to be added.
   */
  def addSiegeCard(info: (String, Int, IEffect), n: Int): Unit = {
    addCard[SiegeFactory](info, n, SiegeFactory)
  }

  /** Removes a card from the pool entirely or by an amount.
   *
   * Unused.
   *
   * @param info Information of the card.
   * @param n amount of cards to remove. If 0, the card gets entirely removed from the pool.
   * */
  def removeFromPool(info: (String, Int, IEffect), n: Int = 0): Unit = {
    if (n == 0){
      Pool.remove(info)
      productionAssignation.remove(info)
    }else if ( n > 0) {
      Pool(info) -= n
      if Pool(info) <= 0 then {
        Pool.remove(info)
        productionAssignation.remove(info)
      }
    }
  }
}
