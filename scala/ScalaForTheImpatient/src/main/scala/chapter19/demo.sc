package chapter19

object demo {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  import scala.util.parsing.combinator._
  class ExprParser extends RegexParsers {
    val number = "[0-9]+".r
    
  }
}