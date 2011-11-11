package objects

/**
 * An abstract superclass for concrete Statement implementation classes.
 */
abstract class Statement

/**
 * A binary statement with two non-null children.
 */
abstract class BinaryStatement(left: Statement, right: Statement) extends Statement {
  require(left != null)
  require(right != null)
}

/**
 * Syntax for applicative (side-effect-free) statements.
 */
case class Constant(value: Int) extends Statement
case class Plus(left: Statement, right: Statement) extends BinaryStatement(left, right)
case class Minus(left: Statement, right: Statement) extends BinaryStatement(left, right)
case class Times(left: Statement, right: Statement) extends BinaryStatement(left, right)
case class Div(left: Statement, right: Statement) extends BinaryStatement(left, right)

/**
 * Syntax for imperative statements, that is, those that are interesting because of their
 * side effects.
 */
case class Variable(name: String) extends Statement {
  require(name != null)
}
case class Sequence(statements: Statement*) extends Statement {
  require(statements != null)
  require(! statements.contains(null))
}
case class While(guard: Statement, body: Statement) extends BinaryStatement(guard, body)
case class Assignment(left: Statement, right: Statement) extends BinaryStatement(left, right)

/**
 * Syntax for statements for creating and using records.
 * New is implemented as a non-case class to allow the constructor
 * argument to be by-name. The companion object adds back the syntactic
 * sugar to make it look like a case class.
 */
class New(c: => Clazz) extends Statement {
  require(c != null)
  val clazz = c
}
object New {
  def apply(clazz: => Clazz) = new New(clazz)
  def unapply(s: Statement) = s match {
    case n: New => Some(n.clazz)
    case _ => None
  }
}
case class Selection(receiver: Statement, field: String) extends Statement {
  require(receiver != null)
  require(field != null)
}
case class Message(receiver: Statement, method: String, arguments: Statement*) extends Statement {
  require(receiver != null)
  require(method != null)
  require(arguments != null)
  require(! arguments.contains(null))
}

/**
 * Syntax for classes. Not part of the Statement hierarchy
 * because they appear only as arguments to New Statements.
 * Note that methods are defined in terms of their local variables
 * and their body; arguments are numbered instead of named.
 */
case class Clazz(fields: Seq[String], methods: Seq[(String, (Seq[String], Statement))]) {
  require(fields != null)
  require(! fields.contains(null))
  require(methods != null)
  require(! methods.contains(null))

  def this(fields: String*) = this(fields, Seq())
}
