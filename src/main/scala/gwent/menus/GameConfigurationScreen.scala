package cl.uchile.dcc
package gwent.menus

import gwent.player.*
import gwent.cards.pool.DefaultCardPool
import cl.uchile.dcc.gwent.cards.*
import cl.uchile.dcc.gwent.player.factories.PlayerFactory
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Queue
import scala.util.Random

class GameConfigurationScreen extends IGameConfiguration {
  private var numberOfEnemies:Int = 1
  private val CardPool = new DefaultCardPool()
  private val CardList = CardPool.createCardPool()
  private var PlayerList: ListBuffer[Iplayer] = ListBuffer()
  private var ChosenEnemies: mutable.Queue[String] = mutable.Queue()
  private val namePool: List[String] = List("Germany","Japan","United States of America", "Soviet Union", "Great Britain")
  private var Factory: PlayerFactory = new PlayerFactory()
  private var PlayerName:Option[String] = None

  private def selectRandomEnemies(n:Int):Unit = {
    val names = Random.shuffle(namePool).slice(0,n)
    ChosenEnemies.enqueueAll(names)
  }

  private def createDeck():ArrayBuffer[ICard] = {
    val deck:ArrayBuffer[ICard] = ArrayBuffer()
    deck ++= Random.shuffle(CardList).slice(0,25)
    deck
  }
  override def setNumberOfEnemies(n: Int): Unit = {
    numberOfEnemies = n
  }

  override def setEnemy(name: String): Unit = {
    ChosenEnemies.enqueue(name)
  }
  override def createEnemies():Unit = {
    val k = ChosenEnemies.size
    val check:Boolean = (numberOfEnemies == k)
    if check then {} else selectRandomEnemies(numberOfEnemies - k)

    while(ChosenEnemies.nonEmpty){
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

  override def getPlayerList(): List[Iplayer] = {
    val output = PlayerList.toList
    output
  }

  override def startGame(): Unit = {
    val name = PlayerName.getOrElse(Random.shuffle(namePool).head)
    val deck = createDeck()
    val Player = new Player(name, deck)
    PlayerList.prepend(Player)
  }

}
