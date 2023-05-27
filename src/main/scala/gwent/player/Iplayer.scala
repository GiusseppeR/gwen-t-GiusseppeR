package cl.uchile.dcc
package gwent.player

import gwent.board.*
import gwent.cards.*

import scala.collection.mutable.ArrayBuffer

/** Defines a Player.
 *
 * Each method must be defined in class.
 * Used by Player.
 */
trait Iplayer {
  def getName(): String
  def getInitDeck():ArrayBuffer[ICard]
  def getDeck(): ArrayBuffer[ICard]
  def remainingGems(): Int
  def takeDamage():Unit
  def currentHand(): ArrayBuffer[ICard]
  def playCard(C:ICard): Unit
  def takeCard(n:Int):Unit

  def setBoard(board:Board):Unit
  def getBoard():Board
  def setBoardSide(side: BoardSide):Unit
  def getBoardSide():BoardSide
}
