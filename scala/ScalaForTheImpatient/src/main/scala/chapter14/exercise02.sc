package chapter14

object exercise02 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  
  def swap(p: Tuple2[Int, Int]) = p match {
    case (y, x) => (x, y)
  }                                               //> swap: (p: (Int, Int))(Int, Int)
  
  swap((2, 3))                                    //> res0: (Int, Int) = (3,2)
}