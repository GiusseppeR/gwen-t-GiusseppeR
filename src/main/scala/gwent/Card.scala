package cl.uchile.dcc
package gwent

import java.util.Objects

trait ICard{
  def getName():String
}

abstract class Card(private val name:String) extends ICard {
  override def getName(): String = name
}
abstract class UnitCard(name:String, private var SP:Int) extends Card(name){
  def getSP(): Int = SP
}
class CloseCombat(name:String,SP:Int) extends UnitCard(name,SP){

  override def equals(obj: Any): Boolean = {
    if (obj.isInstanceOf[CloseCombat]) {
      val other =obj.asInstanceOf[CloseCombat]
      (this eq other) ||
        other.getName() == name &&
        other.getSP() == SP
    }else{
      false
    }
  }

  override def hashCode(): Int = {
    Objects.hash(classOf[UnitCard], name, SP)
  }
}
class Siege(name:String,SP:Int) extends UnitCard(name,SP){

  override def equals(obj: Any): Boolean = {
    if (obj.isInstanceOf[Siege]) {
      val other = obj.asInstanceOf[Siege]
      (this eq other) ||
        other.getName() == name &&
          other.getSP() == SP
    }else {
      false
    }
  }

  override def hashCode(): Int = {
    Objects.hash(classOf[UnitCard], name, SP)
  }

}
class Range(name:String,SP:Int) extends UnitCard(name,SP) {

  override def equals(obj: Any): Boolean = {
    if (obj.isInstanceOf[Range]) {
      val other = obj.asInstanceOf[Range]
      (this eq other) ||
        other.getName() == name &&
          other.getSP() == SP
    } else {
      false
    }
  }

  override def hashCode(): Int = {
    Objects.hash(classOf[UnitCard], name, SP)
  }

}
class WeatherCard(name:String) extends Card(name){

  override def equals(obj: Any): Boolean = {
    if (obj.isInstanceOf[WeatherCard]) {
      val other = obj.asInstanceOf[WeatherCard]
      (this eq other) ||
        other.getName() == name
    } else {
      false
    }
  }


  override def hashCode(): Int = {
    Objects.hash(classOf[Card], name)
  }

}
