package chapter15

object exercise04 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  import scala.annotation.varargs
  
  def sum(@varargs ints: Int*): Int = {
    ints.toList.sum
  }                                               //> sum: (ints: Int*)Int
  
  def sum2(@varargs ints: Int*): Int = {
    ints.sum
  }                                               //> sum2: (ints: Int*)Int
}