package misc

object InterfaceCast {

  trait I { def f(): Int }

  trait J { def g(): Int }

  class K extends I with J {
    override def f(): Int = 2
    override def g(): Int = 4
  }

  def main(args: Array[String]) {
    val x = new K
    println((x.asInstanceOf[J]).g())
  }
}

