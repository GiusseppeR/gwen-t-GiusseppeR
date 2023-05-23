package cl.uchile.dcc
package gwent

import scala.collection.mutable.ArrayBuffer
class Board extends IBoard {
  private var weatherZone:ArrayBuffer[WeatherCard] = ArrayBuffer()
  private var playerList: ArrayBuffer[Player] = ArrayBuffer()

  override def addPlayer(P:Player, sideName: String): Unit = {
    try{
      for (player <- playerList) {
        if (P.equals(player) || sideName == player.getBoardSide().getName()) throw
          new InvalidBoardPlacementException()
      }

      P.setBoard(this)
      P.setBoardSide(new BoardSide(sideName))
      playerList += P
    }catch{
      case a:InvalidBoardPlacementException => print("Invalid placement. Board side taken or player already in board")
    }


  }

  override def getPlayerList():ArrayBuffer[Player] = playerList
  override def getWinner(): Option[Player] = {
    var max:Int = 0
    var winner: Option[Player] = None
    var playerPoints:Int = 0
    for(player <- playerList){
      playerPoints = player.getBoardSide().getPoints()
      if (playerPoints > max){
        max = playerPoints
        winner = Some(player)
      } else if (max > 0 && max == playerPoints){
        winner = None
      }
    }
    winner
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
}
