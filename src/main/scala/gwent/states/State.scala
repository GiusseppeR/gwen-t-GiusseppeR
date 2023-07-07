package cl.uchile.dcc
package gwent.states

class State {
  protected def transitionError(targetState: String): Unit = {
    throw new InvalidTransitionException(
      s"Cannot transition from ${getClass.getSimpleName} to $targetState"
    )
  }
}
