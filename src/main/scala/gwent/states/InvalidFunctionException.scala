package cl.uchile.dcc
package gwent.states

/** Function Exception.
 *
 * Thrown when a function call is invalid.
 *
 * @param message Message warning.
 */
class InvalidFunctionException(message:String)
  extends Exception(message)