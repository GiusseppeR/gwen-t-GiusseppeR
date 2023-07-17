package cl.uchile.dcc
package gwent.states

import gwent.states.InvalidTransitionException

/**
 * Represents a generic state.
 */
class State {
  /**Throws an InvalidTransitionException.
   *
   * @param targetState state to be transitioned to.
   */
  protected def transitionError(targetState: String): Unit = {
    throw new InvalidTransitionException(
      s"Cannot transition from ${getClass.getSimpleName} to $targetState"
    )
  }
}
