package cl.uchile.dcc
package gwent.observer

/** Defines a generic observer.
 *
 * @tparam T Type of the notified value.
 */
trait Observer[T]{
  def update(observable: Subject[T], value: T): Unit
}
