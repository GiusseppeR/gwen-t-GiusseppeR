package cl.uchile.dcc
package gwent.states

import gwent.controller.*
class Passed(context:Controller) extends State(context) {
  override def toEOR(): Unit = {
    context.setState(new EndOfRound(context))
  }
  override def Pass(): Unit = toEOR()
  override def Comply(): Unit = {
    /*To be implemented*/
  }
}
