package imperative

object mainFac {

  val store = Map[String, Cell](
    "x" -> Cell(5),
    "i" -> Cell(0),
    "r" -> Cell(0)
  )

  val s =
    Sequence(
      Assignment(Variable("r"), Constant(1)),
      While(
        Minus(Variable("x"), Variable("i")),
        Sequence(
          Assignment(Variable("i"), Plus(Variable("i"), Constant(1))),
          Assignment(Variable("r"), Times(Variable("r"), Variable("i")))
        )
      )
    )

  def main(args: Array[String]) {
    println(store)
    Execute(store)(s)
    println(store)
  }
}
