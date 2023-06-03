package cl.uchile.dcc
package gwent.controller

trait IController {
  def newGame():Unit
  def goToMenu():Unit
  def addToDeck():Unit
  def addCard():Unit
  def setName():Unit
  def startGame():Unit
  def cardSelection():Unit
  def Pass():Unit
  def nextRound():Unit
}
