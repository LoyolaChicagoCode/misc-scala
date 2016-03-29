package imperative

/** A common trait for concrete Statement implementation classes. */
trait Statement

/** A binary statement with two non-null children. */
abstract class BinaryStatement(left: Statement, right: Statement) extends Statement {
  require(left != null)
  require(right != null)
}

/** Applicative (side-effect-free) statements. */
case class Constant(value: Int) extends Statement
case class Plus(left: Statement, right: Statement) extends BinaryStatement(left, right)
case class Minus(left: Statement, right: Statement) extends BinaryStatement(left, right)
case class Times(left: Statement, right: Statement) extends BinaryStatement(left, right)
case class Div(left: Statement, right: Statement) extends BinaryStatement(left, right)

/** Imperative statements, that is, those that are interesting because of their side effects. */
case class Variable(name: String) extends Statement {
  require(name != null)
}
case class Sequence(statements: Statement*) extends Statement {
  require(statements != null)
  require(!statements.contains(null))
}
case class While(guard: Statement, body: Statement) extends BinaryStatement(guard, body)
case class Assignment(left: Statement, right: Statement) extends BinaryStatement(left, right)
