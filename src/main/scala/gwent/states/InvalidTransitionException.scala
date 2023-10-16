package cl.uchile.dcc
package gwent.states

/** Transition exception.
 *
 * Thrown when a transition is invalid.
 *
 * @param message message warning.
 */
class InvalidTransitionException(message: String)
  extends Exception(message)
