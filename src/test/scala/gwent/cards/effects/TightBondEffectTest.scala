package cl.uchile.dcc
package gwent.cards.effects

import gwent.board.*
import gwent.cards.*
import gwent.effects.{NullEffect, TightBondEffect}
import gwent.player.*

import munit.FunSuite

import scala.collection.mutable.ArrayBuffer


class TightBondEffectTest extends FunSuite{
  var Card1: CloseCombat = _
  var Card2: CloseCombat = _
  var Card3: CloseCombat = _
  var Card4: Range = _

  var Board: Board = _
  var Player: Player = _
  override def beforeEach(context: BeforeEach): Unit = {
    Card1 = new CloseCombat("Legionary",10,new TightBondEffect())
    Card2 = new CloseCombat("Legionary",5)
    Card3 = new CloseCombat("Centurion",20)
    Card4 = new Range("Sagittarii", 10)

    Board = new Board()
    Player = new Player("Julius Caesar", ArrayBuffer(Card1,Card2,Card3,Card4))
    Board.addPlayer(Player,"North")
  }
  test("The effect is applied properly, only to the cards of the same name"){
    Player.playCard(Card4)
    Player.playCard(Card2)
    Player.playCard(Card3)
    Player.playCard(Card1)

    assertEquals(Card1.getSP(),20)
    assertEquals(Card2.getSP(), 10)
    assertEquals(Card3.getSP(), 20)
    assertEquals(Card4.getSP(), 10)
  }
}
