package objects

object mainImperative {

  val store: Execute.Store = (Map[String, Cell](
    "x" -> Cell(2),
    "y" -> Cell(3),
    "r" -> Cell(0)
  ), Map())

  val s =
    While(Variable("y"),
      Sequence(
        Assignment(Variable("r"), Plus(Variable("r"), Variable("x"))),
        Assignment(Variable("y"), Minus(Variable("y"), Constant(1)))
      )
    )

  def main(args: Array[String]) {
    println(store)
    Execute(store)(s)
    println(store)
  }
}
