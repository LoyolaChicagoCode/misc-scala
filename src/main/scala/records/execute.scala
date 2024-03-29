package records

import Execute.{Store, Value}

/**
  * A cell for storing a value (either a number or an object).
  */
case class Cell(var value: Value):
  def get = value
  def set(value: Value) = { this.value = value; this }
end Cell

/**
  * A companion object defining a useful Cell instance.
  */
object Cell:
  def apply(i: Int): Cell = Cell(Left(i)) // Left -> number, Right -> object
  val NULL: Cell = Cell(0)
end Cell

/**
  * An interpreter for expressions and statements.
  */
object Execute:

  /**
    * A memory store is a mapping from variable names to storage cells.
    */
  type Store = Map[String, Cell]

  /**
    * An object (instance) is the same as a memory store.
    */
  type Instance = Store

  /**
    * A run-time value is either a number or an object.
    */
  type Value = Either[Int, Instance]

  def apply(store: Store)(s: Statement): Cell = s match
    case Constant(value)    => Cell(Left(value))
    case Plus(left, right)  => binaryOperation(store, left, right, _ + _)
    case Minus(left, right) => binaryOperation(store, left, right, _ - _)
    case Times(left, right) => binaryOperation(store, left, right, _ * _)
    case Div(left, right)   => binaryOperation(store, left, right, _ / _)
    case Variable(name)     => store(name)
    case Assignment(left, right) =>
      val rvalue = apply(store)(right)
      val lvalue = apply(store)(left)
      lvalue.set(rvalue.get)
    case Sequence(statements*) =>
      statements.foldLeft(Cell.NULL)((c, s) => apply(store)(s))
    case While(guard, body) =>
      var gvalue = apply(store)(guard)
      while gvalue.get.isRight || gvalue.get.left.toOption.get != 0 do
        apply(store)(body)
        gvalue = apply(store)(guard)
      Cell.NULL
    case New(Clazz(fields*)) =>
      // create an object based on the list of field names in the clazz
      Cell(Right(Map(fields.map(field => (field, Cell(0)))*)))
    case Selection(record, field) =>
      // assume the expression evaluates to a record (.right)
      // and choose the desired field
      apply(store)(record).get.toOption.get.apply(field)
  end apply

  def binaryOperation(store: Store, left: Statement, right: Statement, operator: (Int, Int) => Int): Cell =
    val l: Int = apply(store)(left).get.left.toOption.get
    val r: Int = apply(store)(right).get.left.toOption.get
    Cell(Left(operator(l, r)))
  
end Execute
