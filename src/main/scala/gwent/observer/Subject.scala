package cl.uchile.dcc
package gwent.observer

/**Defines a generic subject
 *
 * @tparam T Type of the value that gets notified to the observer.
 */
trait Subject[T]{
  def addObserver(observer: Observer[T]): Unit
  def notifyObservers(value: T): Unit
}
