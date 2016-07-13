package chapter13

object excercise08 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val xs: Array[Double] = Array(1, 2, 3, 4, 5, 6) //> xs  : Array[Double] = Array(1.0, 2.0, 3.0, 4.0, 5.0, 6.0)
  val cols: Int = 3                               //> cols  : Int = 3
  
  def func(xs: Array[Double], cols: Int) =
    xs.grouped(cols).toArray                      //> func: (xs: Array[Double], cols: Int)Array[Array[Double]]
    
  func(xs, cols)                                  //> res0: Array[Array[Double]] = Array(Array(1.0, 2.0, 3.0), Array(4.0, 5.0, 6.0
                                                  //| ))
}