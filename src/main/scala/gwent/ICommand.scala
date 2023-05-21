package cl.uchile.dcc
package gwent

trait ICommand {
  def execute(B: IBoard):Unit
}
