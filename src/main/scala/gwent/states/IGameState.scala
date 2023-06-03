package cl.uchile.dcc
package gwent.states

trait IGameState {
  def toGameConfiguration():Unit
  def toGameStart():Unit
  def toFirst():Unit
  def toSecond():Unit
  def toPassed():Unit
  def toEOR():Unit
  def toRoundStart():Unit
  def toEndOfGame():Unit
}
