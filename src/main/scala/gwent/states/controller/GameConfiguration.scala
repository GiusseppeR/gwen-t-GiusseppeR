package cl.uchile.dcc
package gwent.states.controller

import gwent.controller.*

import cl.uchile.dcc.gwent.player.*

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.ArrayBuffer
import scala.util.Random
import gwent.cards.pool.*
import gwent.player.factories.*
import gwent.cards.*
import gwent.board.*
class GameConfiguration(context:Controller) extends ControllerState(context){
  private var numberOfRandomEnemies: Int = 0

  private val Pool = new CardPool()

  private var PlayerList: ListBuffer[Player] = ListBuffer()
  private var ChosenEnemies: mutable.Queue[String] = mutable.Queue()
  private val namePool: List[String] = List("Germany", "Japan", "United States of America", "Soviet Union", "Great Britain")

  private var Factory: PlayerFactory = new PlayerFactory()
  private var PlayerName: Option[String] = None
  private var User:Option[Player] = None

  private def selectRandomEnemies(n: Int): Unit = {
    val names = Random.shuffle(namePool).slice(0, n)
    ChosenEnemies.enqueueAll(names)
  }

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
  override def setNumberOfRandomEnemies(n: Int): Unit = {
    numberOfRandomEnemies = n
  }
  override def setEnemy(name: String): Unit = {
    ChosenEnemies.enqueue(name)
  }
  private def createEnemies(): Unit = {
    if ChosenEnemies.isEmpty && numberOfRandomEnemies == 0 then numberOfRandomEnemies += 1
    selectRandomEnemies(numberOfRandomEnemies)
    while (ChosenEnemies.nonEmpty) {
      val name = ChosenEnemies.dequeue
      val deck = createDeck()
      Factory.setName(name)
      Factory.setDeck(deck)
      PlayerList.prepend(Factory.createPlayer())
    }
  }

  override def setPlayerName(name: String): Unit = {
    PlayerName = Some(name)
  }

  override def gameStartSettings: (Player, ListBuffer[Player]) = {
    createEnemies()
    val name = PlayerName.getOrElse(Random.shuffle(namePool).head)
    val deck = createDeck()
    val Player = new Player(name, deck)
    User = Some(Player)
    PlayerList.prepend(Player)

    val output = Random.shuffle(PlayerList)
    val board = new Board()
    PlayerList.foreach(p => board.addPlayer(p, p.getName()) )
    (Player,output)
  }
  override def toMainMenu(): Unit = {
    context.setState(new MainMenu(context))
  }

  override def toIdle(): Unit = {
    context.setState(new Idle(context))
  }

}
