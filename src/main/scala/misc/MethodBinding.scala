package misc

object MethodBinding {
  def main(args: Array[String]): Unit = {
    val x = new Dog2()
    // ...
    x.feed()
    x.g()
    x.f()
  }
}

abstract class Animal2 extends AnyRef {
  def feed(): Unit
  def f(): Unit = println("Animal2.f")
  def g() = this.f()
}

class Dog2 extends Animal2 {
  override def feed(): Unit = println("Dog.feed")
  override def f(): Unit = println("Dog2.f")
}

abstract class Cat2 extends Animal2 {
  override def feed() = println("Cat.feed")
}
