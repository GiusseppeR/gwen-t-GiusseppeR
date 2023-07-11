package cl.uchile.dcc
package gwent.player.factories

import scala.collection.mutable.ArrayBuffer
import gwent.player.*
import gwent.cards.*

class PlayerFactory extends IPlayerFactory {
  private var name:Option[String] = None
  private var deck:Option[ArrayBuffer[ICard]] = None
  override def setName(n:String): Unit = {
    name = Some(n)
  }
  override def setDeck(d:ArrayBuffer[ICard]): Unit = {
    deck = Some(d)
  }
  override def createPlayer():Iplayer = {
    new Player(name.get,deck.get)
  }
}
