package cl.uchile.dcc
package gwent.notifications

import gwent.controller.*

trait PlayerControllerNotification {
  def open(controller: Controller):Unit
}
