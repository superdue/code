package week6

object sets {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val s = 1 to 5 toSet                            //> s  : scala.collection.immutable.Set[Int] = Set(5, 1, 2, 3, 4)
  
  s.size                                          //> res0: Int = 5
  
  s map (_ + 2)                                   //> res1: scala.collection.immutable.Set[Int] = Set(5, 6, 7, 3, 4)
}