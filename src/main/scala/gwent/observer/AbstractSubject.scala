package cl.uchile.dcc
package gwent.observer

/**Implements the Subject trait.
 *
 * @tparam T Type of the value that gets notified to the observer.
 */
class AbstractSubject[T] extends Subject[T] {
  /**List of observers.
   */
  protected var observers:List[Observer[T]] = Nil

  /**Adds observers to the list.
   *
   * @param observer Observer to be added.
   */
  override def addObserver(observer: Observer[T]): Unit = {
    observers = observer::observers
  }

  /**Notifies observers about a change in the subject.
   *
   * @param value Value that gets notified.
   */
  override def notifyObservers(value: T): Unit = {
    observers.foreach(o => o.update(this,value))
  }
}
