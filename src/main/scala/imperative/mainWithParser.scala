package imperative

object mainWithParser {

  val store = Map[String, Cell](
    "x" -> Cell(2),
    "y" -> Cell(3),
    "r" -> Cell(0)
  )

  val s = "while (y) { r = r + x, y = y - 1 }"

  def main(args: Array[String]): Unit = {
    println(s)
    val p = StatementParser.parseAll(StatementParser.statement, s)
    println(p)
    println(store)
    Execute(store)(p.get)
    println(store)
  }
}
