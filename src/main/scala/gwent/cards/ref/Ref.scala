package cl.uchile.dcc
package gwent.cards.ref

import gwent.cards.*

/** Defines a series of singletons.
 *
 * Each object is meant to be used as reference of unit cards for the effects.
 * They are, however, not cards. And cannot be passed as such.
 *
 * They only implement the typeCheck method, which checks if a certain cards coincides with type set in the reference.
 *
 *
 */
trait Ref {
  def typeCheck(C:IUnitCard):Boolean
}
