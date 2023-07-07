package cl.uchile.dcc
package gwent.notifications

import gwent.controller.IController

trait PlayerControllerNotification {
  def open(controller: IController):Unit
}
