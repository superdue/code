package chapter14

object exercise03 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // TODO 如何添加泛型？
  def swap(lst: Array[Int]) = lst match {
    case Array(x, y, rest @ _*) => Array(y, x) ++ rest
    case _ => lst
  }                                               //> swap: (lst: Array[Int])Array[Int]
  
  swap(Array(3,2,1))                              //> res0: Array[Int] = Array(2, 3, 1)
}