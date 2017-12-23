package records

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
  require(!statements.contains(null))
}
case class While(guard: Statement, body: Statement) extends BinaryStatement(guard, body)
case class Assignment(left: Statement, right: Statement) extends BinaryStatement(left, right)

/**
  * Syntax for statements for creating and using records.
  */
case class New(clazz: Clazz) extends Statement {
  require(clazz != null)
}
case class Selection(receiver: Statement, field: String) extends Statement {
  require(receiver != null)
  require(field != null)
}

/**
  * Syntax for record types. Not part of the Statement hierarchy
  * because they appear only as arguments to New Statements.
  */
case class Clazz(fields: String*) {
  require(fields != null)
  require(!fields.contains(null))
}
