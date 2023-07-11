package cl.uchile.dcc
package gwent.observer

class AbstractSubject[T] extends Subject[T] {
  protected var observers:List[Observer[T]] = Nil

  override def addObserver(observer: Observer[T]): Unit = {
    observers = observer::observers
  }

  override def notifyObservers(value: T): Unit = {
    observers.foreach(o => o.update(this,value))
  }
}
