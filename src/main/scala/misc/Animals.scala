package misc

import scala.collection.mutable.*

trait Animal:
  var name: String
  def printName: Unit
  def canMate(a: Animal): Boolean
  def speak: Unit
  def birth: Unit
end Animal

trait Mammal extends Animal:
  def printName = print("My name is " + name)
  def canMate(a: Animal) = a.getClass.eq(this.getClass)
  def birth = print("live")
  def birth(n: Int) = print("live" + n)
end Mammal

class Dog(var name: String) extends Mammal:
  def speak = print("woof")
end Dog

class Cat(var name: String) extends Mammal:
  def speak = print("miaow")
end Cat

class Platypus(var name: String) extends Mammal:
  def speak = print("quack")
  override def birth = print("eggs")
  override def birth(n: Int) = print("eggs" + n)
end Platypus

object Animals:
  def main(args: Array[String]): Unit =
    //TODO: confirm that ArrayBuffer is the most appropriate
    //data structure in this case
    val zoo = new ArrayBuffer[Animal]()

    zoo += new Cat("Jerry")
    zoo += new Dog("Ping")
    zoo += new Cat("Socks")
    zoo += new Dog("Hector")
    zoo += new Platypus("Plap")
    zoo += new Cat("Winston")

    for x <- zoo do
      x.printName
      print(" and I say ")
      x.speak
      print(".  My birth method is ")
      x.birth
      println(".")

    val a1 = new Cat("Flip")
    val a2 = new Cat("Flop")
    val a3 = new Dog("Luke")

    println(a1.canMate(a2))
    println(a1.canMate(a3))

    zoo.apply(0).name = "Rover"
    zoo.apply(0).printName

    println(".")
  
end Animals
