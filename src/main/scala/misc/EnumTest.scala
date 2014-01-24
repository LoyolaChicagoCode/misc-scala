package misc

/*
	Output:

	WHITE
	1627433763
	1927526549
	1147894048

 */
object EnumTest {

  def main(args: Array[String]) {
    Cow.Color.WHITE.compareTo(Cow.Color.SPOTTED)
    println(Cow.Color.WHITE)
    for (c <- Cow.Color.values)
      println(c.hashCode)
  }
}

object Cow {
  object Color extends Enumeration {
    val WHITE = Value("WHITE")
    val SPOTTED = Value("SPOTTED")
    val BROWN = Value("BROWN")
  }
}

