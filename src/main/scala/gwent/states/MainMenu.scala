package cl.uchile.dcc
package gwent.states

import gwent.controller.*
import gwent.states.{ControllerState, GameConfiguration}

/**Main menu state
 *
 * The user can go to gameConfiguration screen.
 *
 * @param context A controller as context.
 */
class MainMenu(context:Controller) extends ControllerState(context) {
  override def toGameConfiguration(): Unit = {
    context.setState(new GameConfiguration(context))
  }

}
