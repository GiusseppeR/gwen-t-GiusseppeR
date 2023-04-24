package cl.uchile.dcc
package gwent

trait ICard{
  def getName:String
}

abstract class Card(private val name:String){
  def getName() = name
}
abstract class UnitCard(name:String, private var SP:Int) extends Card(name){
  def getSP():Int = SP
}
class CloseCombat(name:String,SP:Int) extends UnitCard(name,SP){

}
class Siege(name:String,SP:Int) extends UnitCard(name,SP){

}
class Range(name:String,SP:Int) extends UnitCard(name,SP) {

}
class WeatherCard(name:String) extends Card(name){

}
