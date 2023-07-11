package cl.uchile.dcc
package gwent.notifications

import gwent.controller.*
import gwent.player.*
import gwent.observer.*
trait PlayerControllerNotification {
  def open(controller: Controller):Unit
}
