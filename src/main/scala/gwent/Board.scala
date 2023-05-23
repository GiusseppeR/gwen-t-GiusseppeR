package cl.uchile.dcc
package gwent

import scala.collection.mutable.ArrayBuffer
class Board extends IBoard {
  private var weatherZone:Option[WeatherCard] = None
  private var playerList: ArrayBuffer[Player] = ArrayBuffer()

  override def addPlayer(P:Player, sideName: String): Unit = {
    P.setBoard(this)
    P.setBoardSide(new BoardSide(sideName))
    playerList += P
  }

  override def getPlayerList():ArrayBuffer[Player] = playerList
  override def getWinner(): Option[Player] = {
    var max:Int = playerList(0).getBoardSide().getPoints()
    var winner: Option[Player] = None
    var tie:Boolean = false
    for(player <- playerList){
      var playerPoints:Int = player.getBoardSide().getPoints()
      if (playerPoints > max){
        max = playerPoints
        winner = Some(player)
        tie = false
      } else if (max == playerPoints){
        tie = true
      }
    }
    if (max == playerList(0).getBoardSide().getPoints() && !tie){
      winner = Some(playerList(0))
    }
    winner
  }

  override def getCurrentWeatherCard(): WeatherCard = weatherZone.get

  override def setWeatherCard(C:WeatherCard):Unit = {
    weatherZone = Some(C)
  }
}
