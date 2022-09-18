package misc

object OrgChart:
  def main(args: Array[String]): Unit = ()
    // TODO flesh out
end OrgChart

trait Node:
  def size(): Int
end Node

class Person(val name: String) extends Node:
  override def size() = 1
  override def toString() = "P(" + name + ")"
end Person

class Division extends Node:
  def setManager(p: Person) = ()
  def getManager(): Option[Person] = None
  def getChildren(): List[Node] = List.empty
  def addChild(node: Node) = ()
  def size(): Int = 0
  override def toString() = "D(" + getManager() + ")"

end Division
