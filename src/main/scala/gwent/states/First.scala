package cl.uchile.dcc
package gwent.states

import gwent.controller.*
class First(context:Controller) extends State(context) {
  override def toPassed(): Unit = {
    context.setState(new Passed(context))
  }

  override def toSecond(): Unit = {
    context.setState(new Second(context))
  }
  override def Comply(): Unit = {
    toSecond()
  }

  override def Pass(): Unit = toPassed()
}
