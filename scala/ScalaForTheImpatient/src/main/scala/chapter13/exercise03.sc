package chapter13

object excercise03 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val xs = List(-1,0,1)                           //> xs  : List[Int] = List(-1, 0, 1)
  
  def func(xs: List[Int]): List[Int] = xs.filter(_ != 0)
                                                  //> func: (xs: List[Int])List[Int]
  
  func(xs)                                        //> res0: List[Int] = List(-1, 1)
  
  // 另外一种解法
  
  def removeAllZeros(ints: List[Int]): List[Int] = {
    ints.filterNot { _ == 0 }
  }                                               //> removeAllZeros: (ints: List[Int])List[Int]
  
  removeAllZeros(xs)                              //> res1: List[Int] = List(-1, 1)
}