package cl.uchile.dcc
package gwent.states

import gwent.controller.*

class Second(context:Controller) extends State(context) {
  override def toPassed(): Unit = {
    context.setState(new Passed(context))
  }

  override def toFirst(): Unit = {
    context.setState(new First(context))
  }
  override def Comply(): Unit = {
    toFirst()
  }
  override def Pass(): Unit = toPassed()
}
