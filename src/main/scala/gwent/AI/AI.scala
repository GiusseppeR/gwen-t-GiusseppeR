package cl.uchile.dcc
package gwent.AI

import gwent.player.*
trait AI {
  def apply(P:Player):Unit
}
