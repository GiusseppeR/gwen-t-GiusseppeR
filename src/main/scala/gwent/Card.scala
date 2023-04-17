package cl.uchile.dcc
package gwent

trait ICard{
  def getName:String
}

abstract class Card{
}
abstract class UnitCard extends Card{

}
class CloseCombat extends UnitCard{

}
class Siege extends UnitCard{

}
class Range extends UnitCard {

}
class WeatherCard extends Card{

}
