package chapter14

object exercise09 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def sum(lst: List[Option[Int]]) = lst.map(_.getOrElse(0)).sum
                                                  //> sum: (lst: List[Option[Int]])Int
  
  val x = List(Some(1), None, Some(2), None, Some(3))
                                                  //> x  : List[Option[Int]] = List(Some(1), None, Some(2), None, Some(3))
  
  sum(x)                                          //> res0: Int = 6
}