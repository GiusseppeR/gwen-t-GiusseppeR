package cl.uchile.dcc
package gwent.board

import gwent.cards.*
import gwent.player.*
import gwent.board.*

import scala.collection.mutable.ArrayBuffer
class Board extends IBoard {
  private var weatherZone:ArrayBuffer[WeatherCard] = ArrayBuffer(new WeatherCard("Clear Weather"))
  private var playerList: ArrayBuffer[Player] = ArrayBuffer()

  override def addPlayer(P:Player, sideName: String): Unit = {
    try{
      for (player <- playerList) {
        if (P.equals(player) || sideName == player.getBoardSide().getName()) throw
          new Exception()
      }

      P.setBoard(this)
      P.setBoardSide(new BoardSide(sideName))
      playerList += P
    }catch{
      case a:Exception => print("Invalid placement. Board side taken or player already in board")
    }


  }

  override def getPlayerList():ArrayBuffer[Player] = playerList
  override def getWinner(): Player = {
    var max:Int = 0
    var winner: Option[Player] = None
    var playerPoints:Int = 0
    var tie:Boolean = false
    for(player <- playerList){
      playerPoints = player.getBoardSide().getPoints()
      if (playerPoints > max){
        max = playerPoints
        winner = Some(player)
        tie = false
      } else if (max > 0 && max == playerPoints){
        tie = true
      }
    }
    if(!tie) {
      winner.get
    }else{
      new Player("",ArrayBuffer())
    }

  }

  override def getCurrentWeatherCard(): WeatherCard = weatherZone(0)

  override def setWeatherCard(C:WeatherCard):Unit = {
    weatherZone.prepend(C)
  }

  override def equals(obj: Any): Boolean = {
    if (obj.isInstanceOf[Board]) {
      val other = obj.asInstanceOf[Board]
      (this eq other)
    } else {
      false
    }
  }

  override def hashCode(): Int = {
    System.identityHashCode(this)
  }
}
