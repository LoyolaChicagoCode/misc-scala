package imperative

object main {

  /**
    * The global memory initialized as follows:
    * x = 2 , y = 3 , r = 0
    */
  val store = Map[String, LValue[Int]](
    "x" -> Cell(2),
    "y" -> Cell(3),
    "r" -> Cell(0)
  )

  /**
    * The program to be interpreted:
    * while (y) { r = r + x , y = y - 1 }
    */
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
