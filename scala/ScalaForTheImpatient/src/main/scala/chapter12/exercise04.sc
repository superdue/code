package chapter12

object exercise04 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def factorial(n: Int): Int =
    (1 to n).foldLeft(1)(_ * _)                   //> factorial: (n: Int)Int
    
  factorial(5)                                    //> res0: Int = 120
  factorial(0)                                    //> res1: Int = 1
  factorial(-1)                                   //> res2: Int = 1
}