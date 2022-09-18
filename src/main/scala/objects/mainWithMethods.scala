package objects

object mainWithMethods:

  /*
  class MyInt {
    public Object value;
    public Object set(Object arg0) {
      this.value = arg0;
    }
    public Object plus(Object arg0) {
      return this.value + arg0;
    }
    // ...
  }
 */

  def MyInt(): Clazz = Clazz(
    Seq("value"),
    Seq(
      "echo" ->
        (Seq(), Variable("0")),
      "always7" ->
        (Seq(), Message(Variable("this"), "echo", Constant(7))),
      "set" ->
        (Seq(), Assignment(Selection(Variable("this"), "value"), Variable("0"))),
      "get" ->
        (Seq(), Selection(Variable("this"), "value")),
      "plus" ->
        (Seq(), Plus(Selection(Variable("this"), "value"), Variable("0"))),
      "times" ->
        (Seq("result"), Sequence(
          Assignment(Variable("result"), Constant(0)),
          While(Variable("0"), Sequence(
            Assignment(Variable("0"), Minus(Variable("0"), Constant(1))),
            Assignment(Variable("result"), Plus(Variable("result"), Selection(Variable("this"), "value")))
          )),
          Variable("result")
        )),
      "fact" ->
        (Seq("result", "recurse", "aux"), Sequence(
          Assignment(Variable("recurse"), Selection(Variable("this"), "value")),
          Assignment(Variable("result"), Constant(1)),
          While(Variable("recurse"), Sequence(
            Assignment(Variable("recurse"), Constant(0)),
            Assignment(Variable("aux"), New(MyInt())),
            Message(Variable("aux"), "set", Minus(Selection(Variable("this"), "value"), Constant(1))),
            Assignment(Variable("result"), Message(Variable("this"), "times", Message(Variable("aux"), "fact")))
          )),
          Variable("result")
        ))
    )
  )

  /*
   * var a, b, d, e, f, g;
   */
  val store: Execute.Store = (Map[String, Cell](
    "a" -> Cell(0),
    "b" -> Cell(0),
    "d" -> Cell(0),
    "e" -> Cell(0),
    "f" -> Cell(0),
    "g" -> Cell(0)
  ), Map())

  /*
   * var a, b, d, e, f, g;
   * a = new MyInt();
   * a.set(5);
   * b = a.times(7);
   * d = a.fact();
   * e = new MyInt();
   * e.set(6);
   * f = e.times(5);
   * g = e.fact();
   */
  val c = Sequence(
    Assignment(Variable("a"), New(MyInt())),
    Message(Variable("a"), "set", Constant(5)),
    Assignment(Variable("b"), Message(Variable("a"), "times", Constant(7))),
    Assignment(Variable("d"), Message(Variable("a"), "fact")),
    Assignment(Variable("e"), New(MyInt())),
    Message(Variable("e"), "set", Constant(6)),
    Assignment(Variable("f"), Message(Variable("e"), "times", Constant(5))),
    Assignment(Variable("g"), Message(Variable("e"), "fact"))
  )

  def main(args: Array[String]): Unit =
    //    println(store)
    Execute(store)(c)
    //    println(store)
    println(store._1("a"))
    println(store._1("b"))
    println(store._1("d"))
    println(store._1("e"))
    println(store._1("f"))
    println(store._1("g"))
  
end mainWithMethods
