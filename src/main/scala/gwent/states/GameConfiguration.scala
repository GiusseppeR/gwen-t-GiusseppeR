package cl.uchile.dcc
package gwent.states

import gwent.board.*
import gwent.cards.*
import gwent.cards.pool.*
import gwent.controller.*
import gwent.player.*
import gwent.player.factories.*
import gwent.states.{ControllerState, Idle, MainMenu}

import cl.uchile.dcc.gwent.exceptions.{InvalidNameException, NegativeAmountException, NoPlayerNameException}

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.util.Random

/** Game configuration state.
 * The user can select a number of random enemies, set enemy names, set player name and start a game.
 *
 * If no enemies are set, then the game starts with a random enemy.
 * If no player name is set, then the game starts with a random name from a pool of names.
 *
 * @param context A controller as context.
 */
class GameConfiguration(context:Controller) extends ControllerState(context){
  private var numberOfRandomEnemies: Int = 0

  private val Pool = new CardPool()

  private var PlayerList: ListBuffer[Player] = ListBuffer()
  private var ChosenEnemies: mutable.Queue[String] = mutable.Queue()
  private val namePool: List[String] = List("Germany", "Japan", "United States of America", "Soviet Union", "Great Britain")

  private var Factory: PlayerFactory = new PlayerFactory()
  private var PlayerName: Option[String] = None
  private var User:Option[Player] = None

  /** Puts a number of random enemies to the queue.
   *
   * It can select names from the namePool that are not in the queue
   * and are not equal to the player name.
   *
   * if the number of random enemies set is higher than the number of possible enemies, it
   * just creates all the enemies it can.
   *
   * @param n Number of random enemies.
   */
  private def selectRandomEnemies(n: Int): Unit = {
    var filter = namePool.filter(p => !ChosenEnemies.contains(p))
    if PlayerName.isDefined then filter = filter.filter(p => p != PlayerName.get)
    var enemies = n
    if filter.length < n then enemies = filter.length
    val names = Random.shuffle(filter).slice(0, enemies)
    ChosenEnemies.enqueueAll(names)
  }

  /**Creates a random deck.
   *
   * Randomly chooses the information of a card in Pool,
   * creates a card with that information (delegates to cardPool)
   * and adds it to an ArrayBuffer until it has 25 cards.
   *
   * @return The created deck.
   */
  private def createDeck(): ArrayBuffer[ICard] = {
    val output: ArrayBuffer[ICard] = ArrayBuffer()
    val newPool = Pool.copy
    val cards = newPool.getCardsInfo

    val cardsLength = cards.length
    var count = 0

    while (count < 25) {
      val card = cards(Random.nextInt(cardsLength))
      val addCard = newPool.addToDeck(output, card)
      if addCard then count += 1
    }
    output
  }

  /**Sets the amount of randomized enemies.
   *
   * @param n number of random enemies.
   */
  override def setNumberOfRandomEnemies(n: Int): Unit = {
    try{
      if n < 0 then throw new NegativeAmountException()
      numberOfRandomEnemies = n
    }catch {
      case e:NegativeAmountException => print("Please introduce a positive number.")
    }
  }

  /**Adds a name to the enemy queue.
   *
   * If the name is already in the queue, it prints a warning and does nothing.
   *
   * @param name Name of the enemy to be added.
   */
  override def setEnemy(name: String): Unit = {
    try{
      if ChosenEnemies.contains(name) then throw new InvalidNameException()
      if (PlayerName.isDefined) {
        if PlayerName.get == name then throw new InvalidNameException()
      }
      ChosenEnemies.enqueue(name)
    } catch {
      case e:InvalidNameException => println("Invalid enemy name, please try another one.")
    }
  }

  /**Creates enemies according the variables set.
   *
   * If nothing is set, it just makes a random enemy.
   * Uses the player factory.
   */
  private def createEnemies(): Unit = {
    if ChosenEnemies.isEmpty && numberOfRandomEnemies <= 0 then numberOfRandomEnemies += 1
    selectRandomEnemies(numberOfRandomEnemies)
    while (ChosenEnemies.nonEmpty) {
      val name = ChosenEnemies.dequeue
      val deck = createDeck()
      Factory.setName(name)
      Factory.setDeck(deck)
      PlayerList.prepend(Factory.createPlayer())
    }
  }

  /** Sets the player name.
   *
   * If an enemy already has that name, it prints a warning and does nothing.
   *
   * @param name Name of the player.
   */

  override def setPlayerName(name: String): Unit = {
      if !ChosenEnemies.contains(name) then PlayerName = Some(name)
  }

  /**Provides a shuffled list with the players and a reference to the user's player.
   *
   * Calls the createEnemies() method and creates a player with the information set by the user.
   * It also adds every player to a board.
   *
   * It no player name is set, it chooses a random name from the namePool. If it can't, then throws an exception.
   * Exception handled in startGame method (controller).
   *
   *
   * @return List with the players and a reference to the user's player.
   */
  override def gameStartSettings: (Player, ListBuffer[Player]) = {
    if namePool.forall(p => ChosenEnemies.contains(p)) then throw new NoPlayerNameException()
    while(PlayerName.isEmpty){
      val name = Random.shuffle(namePool).head
      setPlayerName(name)
    }
    createEnemies()
    val deck = createDeck()
    val Player = new Player(PlayerName.get, deck)
    User = Some(Player)
    PlayerList.prepend(Player)

    val output = Random.shuffle(PlayerList)
    val board = new Board()
    PlayerList.foreach(p => board.addPlayer(p, p.getName()) )
    (Player,output)
  }

  /**
   * transition to MainMenu
   */
  override def toMainMenu(): Unit = {
    context.setState(new MainMenu(context))
  }

  /**
   * transition to Idle
   */
  override def toIdle(): Unit = {
    context.setState(new Idle(context))
  }

}
