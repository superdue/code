package chapter13

object excercise07 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val prices = List(5.0, 20.0, 9.95)              //> prices  : List[Double] = List(5.0, 20.0, 9.95)
  val quantities = List(10, 2, 1)                 //> quantities  : List[Int] = List(10, 2, 1)
  
  prices zip quantities map { p => p._1 * p._2 } sum
                                                  //> res0: Double = 99.95
  
  val func = ((x: Double, y: Int) => x * y).tupled//> func  : ((Double, Int)) => Double = <function1>
  prices zip quantities map func sum              //> res1: Double = 99.95
  
  // 另外一种解法
  
  (prices zip quantities) map { Function.tupled(_ * _) } sum
                                                  //> res2: Double = 99.95
}