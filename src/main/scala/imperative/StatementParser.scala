package imperative

import scala.util.parsing.combinator.*

object StatementParser extends JavaTokenParsers {

  /** expr ::= term + term | term - term | term */
  def expr: Parser[Statement] = (
    term ~ "+" ~ term ^^ { case l ~ _ ~ r => Plus(l, r) }
    | term ~ "-" ~ term ^^ { case l ~ _ ~ r => Minus(l, r) }
    | term
  )

  /** term ::= factor * factor | factor / factor | factor */
  def term: Parser[Statement] = (
    factor ~ "*" ~ factor ^^ { case l ~ _ ~ r => Times(l, r) }
    | factor ~ "/" ~ factor ^^ { case l ~ _ ~ r => Div(l, r) }
    | factor
  )

  /** factor ::= wholeNumber | ident | ( factor ) */
  def factor: Parser[Statement] = (
    wholeNumber ^^ { case s => Constant(s.toInt) }
    | ident ^^ { case s => Variable(s) }
    | "(" ~> expr <~ ")" ^^ { case e => e }
  )

  /** statement ::= ident = expr | while (expr) statement | { statement , ... , statement } */
  def statement: Parser[Statement] = (
    ident ~ "=" ~ expr ^^ { case s ~ _ ~ r => Assignment(Variable(s), r) }
    | "while" ~ "(" ~> expr ~ ")" ~ statement ^^ { case g ~ _ ~ b => While(g, b) }
    | "{" ~> repsep(statement, ",") <~ "}" ^^ { case ss => Sequence(ss: _*) }
  )
}
