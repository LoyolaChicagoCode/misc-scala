package imperative

/** A cell (l-value) for storing a value. */
case class Cell(var value: Int) {
  def get: Int = value
  def set(value: Int): Cell = { this.value = value; this }
}

/** A companion object defining a useful Cell instance. */
object Cell {
  val NULL = Cell(0)
}

/** An interpreter for expressions and statements. */
object Execute {

  type Store = Map[String, Cell]

  def apply(store: Store)(s: Statement): Cell = s match {
    case Constant(value)    => Cell(value)
    case Plus(left, right)  => Cell(apply(store)(left).get + apply(store)(right).get)
    case Minus(left, right) => Cell(apply(store)(left).get - apply(store)(right).get)
    case Times(left, right) => Cell(apply(store)(left).get * apply(store)(right).get)
    case Div(left, right)   => Cell(apply(store)(left).get / apply(store)(right).get)
    case Variable(name)     => store(name)
    case Assignment(left, right) => {
      val rvalue = apply(store)(right)
      val lvalue = apply(store)(left)
      lvalue.set(rvalue.get)
    }
    case Sequence(statements @ _*) =>
      statements.foldLeft(Cell.NULL.asInstanceOf[Cell])((c, s) => apply(store)(s))
    case While(guard, body) => {
      var gvalue = apply(store)(guard)
      while (gvalue.get != 0) {
        apply(store)(body)
        gvalue = apply(store)(guard)
      }
      Cell.NULL
    }
  }
}
