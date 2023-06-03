package cl.uchile.dcc
package gwent.controller

import gwent.states.*
trait IController {
  def newGame():Unit
  def goToMenu():Unit
  def addToDeck():Unit
  def addCard():Unit
  def setName():Unit
  def startGame():Unit
  def startRound():Unit
  def cardSelection():Unit
  def Pass():Unit
  def nextRound():Unit
  def getState():State
}
