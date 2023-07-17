package cl.uchile.dcc
package gwent.AI

import gwent.player.*

/** Defines an Artificial Intelligence Model
 *
 * The apply method must be defined in class.
 * Used by Controller
 */
trait AI {
  def apply(P:Player):Unit
}
