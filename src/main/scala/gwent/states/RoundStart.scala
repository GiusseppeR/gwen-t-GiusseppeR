package cl.uchile.dcc
package gwent.states

import gwent.controller.*

class RoundStart(context:Controller) extends State(context) {
  override def toFirst(): Unit = {
    context.setState(new First(context))
  }
  override def Comply():Unit = {
    toFirst()
  }
}
