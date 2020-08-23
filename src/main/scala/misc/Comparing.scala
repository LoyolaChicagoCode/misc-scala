package misc

import scala.collection.mutable._
import scala.util._

object Comparing {

  def main(args: Array[String]): Unit = {
    val t1 = new Temp
    t1.tt = 24
    val t2 = new Temp
    t2.tt = 24
    val t3 = new Temp
    t3.tt = 24
    val t4 = new Temp
    t4.tt = 37
    val t5 = new Temp
    t5.tt = 37
    val t6 = new Temp
    t6.tt = 39
    val t7 = new Temp
    t7.tt = 40

    val l1 = new ArrayBuffer[Temp]
    l1 += t1
    l1 += t7
    l1 += t3
    l1 += t4
    l1 += t2
    l1 += t5
    l1 += t6
    println(l1)

    val ascending = new Comparator[Temp] {
      def compare(o1: Temp, o2: Temp) = o1.tt - o2.tt
    }

    val descending = new Comparator[Temp] {
      def compare(o1: Temp, o2: Temp) = o2.tt - o1.tt
    }

    Sorting.quickSort(l1.toArray) //ascending
    println(l1)
    Sorting.quickSort(l1.toArray)
    println(l1)

    var s = new TreeSet[Temp]
    val t8 = new Temp
    t1.tt = 24
    s += t8

    val t9 = new Temp
    t9.tt = 24
    s += t9

    val t10 = new Temp
    t10.tt = 37
    s += t10

    val t11 = new Temp
    t11.tt = 19
    s += t11

    for (c <- s) {
      println(c)
    }

    var l2 = new ArrayBuffer[Temp]

    val t12 = new Temp
    t12.tt = 24
    l2 += t12

    l2 += (new CTemp(19))
    l2 += (new CTemp(27))
    l2 += (new CTemp(11))

    println(l2)
    Sorting.quickSort(l2.toArray) // use CTemp.compareTo
    System.out.println(l2)
    Sorting.quickSort(l2.toArray) //TODO: it has to be descending!!!
    System.out.println(l2)

    var s2 = new TreeSet[Temp]
    s2 += new CTemp(24)
    s2 += new CTemp(19)
    s2 += new CTemp(27)
    s2 += new CTemp(19)
    for (c <- s2) {
      println(c)
    }
    println(l1)
  }
}

//Should we be using a trait here? I feel that this should somehow be done
//with functional programming...
trait Comparator[Temp] {
  def compare(o1: Temp, o2: Temp): Int
}

class Temp extends Ordered[Temp] {
  private var t = 0
  def tt = t
  def tt_=(newT: Int) = t = newT

  override def toString = super.toString() + "[t=" + t + "]"

  override def equals(that: Any) = {
    this == that || that.isInstanceOf[Temp] && this.t == (that.asInstanceOf[Temp]).t
  }

  override def hashCode = t

  def compare(that: Temp) = this.t - that.t
}

class CTemp(private var t: Int) extends Temp {

  def compareTo(that: CTemp): Int = {
    if (this == that) return 0
    this.t - that.t
  }
}

