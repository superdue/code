package week3

object traits {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val sub = new Sub                               //> sub  : week3.Sub = week3.Sub@4229ab3e
  sub.fun1                                        //> res0: String = Sub fun1
  sub.fun2                                        //> res1: String = A fun2
  sub.fun3                                        //> res2: String = B fun3
  sub.fun4                                        //> res3: String = C fun4
  
  val e = new Error("error")                      //> e  : Error = java.lang.Error: error
  val n = null                                    //> n  : Null = null
  
  def x: Boolean = false                          //> x: => Boolean
  val y = if (x) 1 else "stsdfsdring"             //> y  : Any = stsdfsdring
}

abstract class Base {
  def fun1: String
  def fun2: String
}
trait A {
  def fun1: String
  def fun2: String = "A fun2"
}
trait B {
  def fun3: String = "B fun3"
}
trait C {
  def fun4: String = "C fun4"
}
class Sub extends Base with A with B with C {
  def fun1: String = "Sub fun1"
}