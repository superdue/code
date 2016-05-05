package chapter12

object exercise10 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def unless(condition: => Boolean)(block: => Unit) { if (!condition) { block } }
                                                  //> unless: (condition: => Boolean)(block: => Unit)Unit
  
  unless (0 > 1) { println("Unless!") }           //> Unless!
}