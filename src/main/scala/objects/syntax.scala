package objects

/**
  * An abstract superclass for concrete Statement implementation classes.
  */
abstract class Statement

/**
  * A binary statement with two non-null children.
  */
abstract class BinaryStatement(left: Statement, right: Statement) extends Statement

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
case class Variable(name: String) extends Statement
case class Sequence(statements: Statement*) extends Statement
case class While(guard: Statement, body: Statement) extends BinaryStatement(guard, body)
case class Assignment(left: Statement, right: Statement) extends BinaryStatement(left, right)

/**
  * Syntax for statements for creating and using records.
  * New is implemented as a non-case class to allow the constructor
  * argument to be by-name. The companion object adds back the syntactic
  * sugar to make it look like a case class.
  */
class New(val clazz: Clazz) extends Statement
object New:
  def apply(clazz: Clazz) = new New(clazz)
  def unapply(s: Statement) = s match
    case n: New => Some(n.clazz)
    case _      => None
end New

case class Selection(receiver: Statement, field: String) extends Statement
case class Message(receiver: Statement, method: String, arguments: Statement*) extends Statement

/**
  * Syntax for classes. Not part of the Statement hierarchy
  * because they appear only as arguments to New Statements.
  * Note that methods are defined in terms of their local variables
  * and their body; arguments are numbered instead of named.
  */
case class Clazz(fields: Seq[String], methods: Seq[(String, (Seq[String], Statement))]):
  def this(fields: String*) = this(fields, Seq())
end Clazz
